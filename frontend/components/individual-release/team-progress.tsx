import type { releaseProgress } from "@/app/release/release.types";
import ProgressComponent from "./progress-component";

export default function ProgressCharts(prop: { progress: releaseProgress }) {
  return (
    <div className="flex flex-row justify-around content-center">

      <ProgressComponent totalProgress={prop.progress.percent_uat} name={"UAT Testing"}></ProgressComponent>
      <ProgressComponent totalProgress={prop.progress.percent_third_party} name={"3rd Party Testing"}></ProgressComponent>
      <ProgressComponent totalProgress={prop.progress.percent_pt} name={"PT Testing"}></ProgressComponent>
      <ProgressComponent totalProgress={prop.progress.percent_qa} name={"QA Testing"}></ProgressComponent>

    </div>
  )
}