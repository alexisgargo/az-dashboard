import { release } from "@/app/release/release.types";
import ProgressComponent from "./progress-component";

export default function ReleaseInfo(prop: { releaseInfo: release, totalProgress: number}) {
  return (
    <div style={{display:'flex', alignItems:'center'}}>
          <div>
            <h1>Release: {prop.releaseInfo.name}</h1>
            <p>Release ID: {prop.releaseInfo.id_release}</p>
            <p>Release status: {prop.releaseInfo.status}</p>
            <p>Code-cutoff date: {prop.releaseInfo.code_cutoff}</p>
            <p>Release date: {prop.releaseInfo.curr_release_date}</p>
            <p>Release engineer: {prop.releaseInfo.engineer.name}</p>
            {prop.releaseInfo.is_hotfix ? <p>Hotfix</p> : <></>}
            {prop.releaseInfo.is_rollback ? <p>Rolled back</p> : <></>}
            <p>Notes: {prop.releaseInfo.release_note}</p>
          </div>
          <div>
          <ProgressComponent totalProgress={prop.totalProgress} name={"Total Progress"}></ProgressComponent>
          </div>
    </div>
  );
}