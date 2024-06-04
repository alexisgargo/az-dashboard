"use client";
import { Button } from "@nextui-org/button";
import useRelease from "../release.hook";
import Release from "@/components/individual-release/release";
import { useEffect } from "react";
import { getRelease } from "../release.api";
import { useSearchParams } from "next/navigation";

export default function ReleasePage({
    params,
}: Readonly<{ params: { id: number } }>) {
    const {
        release,
        progress,
        issueCount,
        totalProgress,
        setSelectedDate,
        setRelease,
    } = useRelease();

    const date = useSearchParams().get("date");

    useEffect(() => {
        const fetchRelease = async () => {
            setRelease(await getRelease(params.id));
        };
        fetchRelease();
    }, []);

    useEffect(() => {
        console.log(release);
    }, [release]);

    return (
        <div>
            <Button variant="light"> {"<< Return to dashboard"} </Button>

            <Release
                selectedDate={setSelectedDate}
                releaseInfo={release}
                totalProgress={totalProgress}
                progress={progress}
                ticketsCount={issueCount.issues}
                bugsCount={issueCount.bugs}
                date={date}
            ></Release>
        </div>
    );
}
