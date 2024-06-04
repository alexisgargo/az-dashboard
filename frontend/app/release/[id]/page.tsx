"use client";
import useRelease from "../release.hook";
import Release from "@/components/individual-release/release";
import { useEffect, useState } from "react";
import { getRelease } from "../release.api";
import NavBar from "@/components/navbar/navbar";

export default function ReleasePage({ params }: any) {
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
      <NavBar />
      <div className="flex flex-col items-center justify-center">
        <Release
          selectedDate={setSelectedDate}
          releaseInfo={release}
          totalProgress={totalProgress}
          progress={progress}
          ticketsCount={issueCount.issues}
          bugsCount={issueCount.bugs}
        ></Release>
      </div>
    </div>


  );
}