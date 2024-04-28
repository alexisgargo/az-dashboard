"use client";
import ProgressCharts from "@/components/individual-release/team-progress";
import TicketsIssuesCard from "@/components/individual-release/tickets-issues";
import TotalProgress from "@/components/individual-release/total-progress";
import { Button } from "@nextui-org/button";
import { getRelease, getProgress } from "./release.api";
import { release, releaseProgress } from "./release.types";
import { useEffect, useState } from "react";
import { CircularProgress } from "@nextui-org/progress";

export default function ReleasePage() {
    const [release, setRelease] = useState<release>({
        id_release: 0,
        name: "",
        version: "",
        engineer: { name: "", id: 0 },
        code_cutoff: "",
        init_release_date: "",
        curr_release_date: "",
        is_hotfix: false,
        status: "",
        is_rollback: false,
        creation_date: "",
        admin: { admin_name: "", admin_password: "", creation_date: "" },
        last_modification_date: "",
        release_note: "",
    });

    const [progress, setProgress] = useState<releaseProgress>({
        release: release,
        date: "",
        percent_qa: 0,
        percent_uat: 0,
        percent_third_party: 0,
        percent_pt: 0,
    });

    const [totalProgress, setTotalProgress] = useState<number>(0);

    useEffect(() => {
        const fetchRelease = async () => {
            setRelease(await getRelease(1));
        };
        fetchRelease();
    }, []);

    useEffect(() => {
        const fetchProgress = async () => {
            setProgress(await getProgress(release.name, release.version));
        };
        fetchProgress();
    }, [release]);

    useEffect(() => {
        setTotalProgress(
            (progress.percent_qa +
                progress.percent_uat +
                progress.percent_third_party +
                progress.percent_pt) /
                4
        );
    }, [progress]);

    return (
        <div>
            <div className="pb-5">
                <Button variant="light"> {"<< Return to dashboard"} </Button>
            </div>

            <div className="grid grid-flow-row grid-flow-col gap-5">
                <div className="row-span-2 pb-5">
                    <CircularProgress
                        classNames={{
                            svg: "w-96 h-96",
                            value: "text-3xl font-semibold text-white",
                        }}
                        value={totalProgress}
                        color="success"
                        label="Total progress"
                        showValueLabel={true}
                    ></CircularProgress>
                    <TotalProgress releaseInfo={release} />
                </div>

                <div className="flex flex-row gap-5">
                    <div className="content-center">
                        <TicketsIssuesCard ticketsCount={100} bugsCount={51} />
                    </div>
                </div>

                <div className="content-center">
                    <ProgressCharts progress={progress} />
                </div>
            </div>
        </div>
    );
}
