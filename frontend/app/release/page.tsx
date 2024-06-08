"use client";
import { Button } from "@nextui-org/button";
import useRelease from "./release.hook";
import Release from "@/components/individual-release/release";

export default function ReleasePage() {
  const { release, progress, issueCount, totalProgress, setSelectedDate } = useRelease();

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
      ></Release>



    </div>

    
  );
}