import { CircularProgress } from "@nextui-org/progress";
import type { releaseProgress } from "@/app/release/release.types";

export default function ProgressCharts(prop: { progress: releaseProgress }) {
  return (
    <div className="flex flex-row justify-around content-center">
      <CircularProgress
        label="QA Testing"
        color="success"
        value={prop.progress.percent_qa}
        minValue={0}
        maxValue={100}
        classNames={{ svg: "w-64 h-64", value: "text-3xl font-semibold" }}
        showValueLabel={true}
      />

      <CircularProgress
        label="UAT Testing"
        color="success"
        value={prop.progress.percent_uat}
        minValue={0}
        maxValue={100}
        classNames={{ svg: "w-64 h-64", value: "text-3xl font-semibold" }}
        showValueLabel={true}
      />

      <CircularProgress
        label="3rd Party Testing"
        color="success"
        value={prop.progress.percent_third_party}
        minValue={0}
        maxValue={100}
        classNames={{ svg: "w-64 h-64", value: "text-3xl font-semibold" }}
        showValueLabel={true}
      />

      <CircularProgress
        label="PT Testing"
        color="success"
        value={prop.progress.percent_pt}
        minValue={0}
        maxValue={100}
        classNames={{ svg: "w-64 h-64", value: "text-3xl font-semibold" }}
        showValueLabel={true}
      />
    </div>
  )
}