"use client";
import ReleaseCard from "@/components/releases-dashboard/releaseCard";
import { useState } from "react";
import useReleases from "./releases-dashboard.hook";
import CalendarComponent from "@/components/Calendar/Calendar";
import { Button } from "@nextui-org/button";
import { Popover, PopoverTrigger, PopoverContent } from "@nextui-org/popover";
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';

export default function ReleasePage() {
  const { releases, totalProgress, setSelectedDate } = useReleases()
  console.log(releases)

  return (
    <div className="flex flex-row flex-wrap gap-5">
      <Popover placement="left" showArrow={true}>
        <PopoverTrigger>
          <Button isIconOnly variant="light">
            <CalendarMonthIcon />
          </Button>
        </PopoverTrigger>
        <PopoverContent>
          <div className="bg-white">
            <CalendarComponent setDate={setSelectedDate} />
          </div>
        </PopoverContent>
      </Popover>
      {releases.map((release, index) => (
        <ReleaseCard releaseInfo={release} totalRelease={totalProgress[index]} key={index} />
      ))}
    </div>
  );
}
