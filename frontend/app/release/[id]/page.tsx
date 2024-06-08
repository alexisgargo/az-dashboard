"use client";
import useRelease from "../release.hook";
import Release from "@/components/individual-release/release";
import { useEffect } from "react";
import { getRelease } from "../release.api";
import { useSearchParams } from "next/navigation";
import NavBar from "@/components/navbar/navbar";
import CalendarRelease from "@/components/individual-release/calendar-release";

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
        chosenDate,
    } = useRelease();

    const date = useSearchParams().get("date");

    useEffect(() => {
        if (date) {
            setSelectedDate(date);
        } else {
            setSelectedDate(new Date().toJSON().slice(0, 10));
        }
    }, [date]);

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
                <CalendarRelease
                    selectedDate={setSelectedDate}
                    date={chosenDate}
                />
                <Release
                    releaseInfo={release}
                    totalProgress={totalProgress}
                    progress={progress}
                    ticketsCount={issueCount.issues}
                    bugsCount={issueCount.bugs}
                    date={chosenDate}
                />
            </div>
        </div>
    );
}
