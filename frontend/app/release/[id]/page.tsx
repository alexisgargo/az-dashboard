"use client";
import useRelease from "../release.hook";
import Release from "@/components/individual-release/release";
import { useEffect } from "react";
import { getRelease } from "../release.api";
import { useSearchParams } from "next/navigation";
import NavBar from "@/components/navbar/navbar";

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

    return (
        <div>
            <NavBar />
            <div className="flex flex-col items-center justify-center">
                <Release
                    selectedDate={setSelectedDate}
                    releaseInfo={release}
                    totalProgress={totalProgress}
                    progress={progress}
                    ticketsCount={issueCount.issues}
                    bugsCount={issueCount.bugs}
                    date={date}
                />
            </div>
        </div>
    );
}
