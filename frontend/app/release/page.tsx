"use client";
import ProgressCharts from "@/components/individual-release/team-progress";
import TicketsIssuesCard from "@/components/individual-release/tickets-issues";
import TotalProgress from "@/components/individual-release/total-progress";
import CalendarComponent from "@/components/Calendar/Calendar";
import { Button } from "@nextui-org/button";
import { CircularProgress } from "@nextui-org/progress";
import { Popover, PopoverTrigger, PopoverContent } from "@nextui-org/popover";
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import useRelease from "./release.hook";
import { useState } from "react";

export default function ReleasePage() {
  const { release, progress, issueCount, totalProgress, setSelectedDate } = useRelease();

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

        <div className="flex flex-row gap-5 justify-between">
          <div className="content-center">
            <TicketsIssuesCard
              ticketsCount={issueCount.issues}
              bugsCount={issueCount.bugs}
            />
          </div>

          <div>
            <Popover placement="left" showArrow={true}>
              <PopoverTrigger>
                <Button isIconOnly variant="light">
                  <CalendarMonthIcon />
                </Button>
              </PopoverTrigger>
              <PopoverContent>
                <div className="bg-white">
                  <CalendarComponent setDate={setSelectedDate}/>
                </div>
              </PopoverContent>
            </Popover>
          </div>
        </div>

        <div className="content-center">
          <ProgressCharts progress={progress} />
        </div>
      </div>
    </div>
  );
}
