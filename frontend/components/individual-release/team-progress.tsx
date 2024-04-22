"use client";
import { CircularProgress } from "@nextui-org/progress";

export default function ProgressCharts() {
  return (
    <div className="flex flex-row justify-around py-3">
      <CircularProgress
        label="QA Testing"
        color="success"
        value={25}
        minValue={0}
        maxValue={100}
        classNames={{ svg: "w-64 h-64", value: "text-3xl" }}
        showValueLabel={true}
      />

      <CircularProgress
        label="UAT Testing"
        color="success"
        value={50}
        minValue={0}
        maxValue={100}
        classNames={{ svg: "w-64 h-64", value: "text-3xl" }}
        showValueLabel={true}
      />

      <CircularProgress
        label="3rd Party Testing"
        color="success"
        value={75}
        minValue={0}
        maxValue={100}
        classNames={{ svg: "w-64 h-64", value: "text-3xl" }}
        showValueLabel={true}
      />

      <CircularProgress
        label="PT Testing"
        color="success"
        value={100}
        minValue={0}
        maxValue={100}
        classNames={{ svg: "w-64 h-64", value: "text-3xl" }}
        showValueLabel={true}
      />
    </div>
  )
}