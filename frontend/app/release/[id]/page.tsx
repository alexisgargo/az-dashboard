"use client";
import { Button } from "@nextui-org/button";
import useRelease from "../release.hook";
import Release from "@/components/individual-release/release";
import { useEffect, useState } from "react";
import { getRelease } from "../release.api";
import { release } from "@/app/release/release.types";

export default function ReleasePage({params}: any) {
  const { release, progress, issueCount, totalProgress, setSelectedDate, setRelease } = useRelease();


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
      ></Release>



    </div>

    
  );
}