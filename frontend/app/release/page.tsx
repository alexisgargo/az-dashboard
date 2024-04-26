//import getData from "./release.api";
"use client";
import ProgressCharts from "@/components/individual-release/team-progress";
import TicketsIssuesCard from "@/components/individual-release/tickets-issues";
import TotalProgress from "@/components/individual-release/total-progress";
import { Button } from "@nextui-org/button";
import { getRelease } from "./release.api";
import { release } from "./release.types";
import { useEffect, useState } from "react";

export default function ReleasePage() {
    //const personajes = await getData();

    // let release: release = {} as release;

    const [release, setRelease] = useState<release>({
        id_release: 0,
        name: "",
        label: "",
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

    // const handleDateChange = () => {
    // }

    useEffect(() => {
        const fetchRelease = async () => {
            setRelease(await getRelease(1));
            console.log(release);
        };
        fetchRelease();
    }, []);

    // const release: release = await getRelease(1);
    // console.log(release);

    const progressMock = {
        id_release: 42,
        date: "2024-04-25",
        percent_qa: 52,
        percent_uat: 73,
        percent_third_party: 29,
        percent_pt: 91,
    };

    // const releaseMock = {
    //   id_release: 42,
    //   name: "Release name",
    //   label: "REL",
    //   engineer: "David García",
    //   code_cutoff: "2024-01-01",
    //   init_release_date: "2024-04-30",
    //   end_release_date: "2024-04-30",
    //   is_hotfix: true,
    //   status: "On track",
    //   is_rollback: true,
    //   creation_date: "2024-01-02",
    //   admin: "Kike Sanchez",
    //   last_modification_date: "2024-01-02",
    //   release_note: "Pretty cool ngl."
    // }

    return (
        <div>
            <div className="pb-5">
                <Button variant="light"> {"<< Return to dashboard"} </Button>
            </div>

            <div className="grid grid-flow-row grid-flow-col gap-5">
                <div className="row-span-2 pb-5">
                    <TotalProgress releaseInfo={release} />
                </div>

                <div className="flex flex-row gap-5">
                    <div className="content-center">
                        <TicketsIssuesCard ticketsCount={100} bugsCount={51} />
                    </div>
                </div>

                <div className="content-center">
                    <ProgressCharts progress={progressMock} />
                </div>
            </div>
        </div>
    );
}
