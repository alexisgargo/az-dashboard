"use client";
import ReleaseCard from "@/components/releases-dashboard/release-card";
import useReleases from "./releases-dashboard/releases-dashboard.hook";
import CalendarComponent from "@/components/Calendar/Calendar";
import { Button } from "@nextui-org/button";
import { Popover, PopoverTrigger, PopoverContent } from "@nextui-org/popover";
import CalendarMonthIcon from "@mui/icons-material/CalendarMonth";
import DashboardBottom from "@/components/releases-dashboard/dashboard-bottom";
import { useState } from "react";
import NavBar from "@/components/navbar/navbar";

export default function ReleasePage() {
    const { releases, totalProgress, metrics, chosenDate, setSelectedDate } =
        useReleases();

    return (
        <div>
            <NavBar />
            <div className="mx-auto max-w-7xl">
                <div className="pt-5">
                    <h1 className="text-xl"> Autozone B2B Releases</h1>
                    <div className="flex justify-end">
                        <Popover placement="left" showArrow={true}>
                            <PopoverTrigger>
                                <Button variant="light">
                                    <p> {chosenDate} </p>
                                    <CalendarMonthIcon />
                                </Button>
                            </PopoverTrigger>
                            <PopoverContent>
                                <div className="bg-white">
                                    <CalendarComponent
                                        setDate={setSelectedDate}
                                    />
                                </div>
                            </PopoverContent>
                        </Popover>
                    </div>
                    <h1 className="text-4xl font-bold">In-Progress Releases</h1>
                    <div className="flex flex-row gap-7 py-5 overflow-auto">
                        {releases.map((release, index) => (
                            <ReleaseCard
                                releaseInfo={release}
                                totalRelease={totalProgress[index]}
                                key={index}
                                date={chosenDate}
                            />
                        ))}
                    </div>
                    <DashboardBottom metrics={metrics} />
                </div>
            </div>
        </div>
    );
}
