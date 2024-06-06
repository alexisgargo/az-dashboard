"use client";
import ReleaseCard from "@/components/releases-dashboard/releaseCard";
import useReleases from "./releases-dashboard.hook";
import CalendarComponent from "@/components/Calendar/Calendar";
import { Button } from "@nextui-org/button";
import { Popover, PopoverTrigger, PopoverContent } from "@nextui-org/popover";
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';

export default function ReleasePage() {
  const { releases, totalProgress, chosenDate, setSelectedDate } = useReleases()
  console.log(releases)

  return (
    <div className="py-5">
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
              <CalendarComponent setDate={setSelectedDate} />
            </div>
          </PopoverContent>
        </Popover>
      </div>
      <h1 className="text-4xl font-bold"> In-Progress Releases</h1>
      <div className="flex flex-row gap-5 py-5 overflow-auto">
        {releases.map((release, index) => (
          <ReleaseCard releaseInfo={release} totalRelease={totalProgress[index]} key={index} />
        ))}
      </div>
    </div>
  );
}
