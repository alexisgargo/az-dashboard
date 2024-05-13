"use client";
import ReleaseCard from "@/components/releases-dashboard/releaseCard";
import { useState } from "react";

export default function ReleasePage() {

  return (
    <div className="flex flex-row flex-wrap gap-5">
      <ReleaseCard />
    </div>
  );
}
