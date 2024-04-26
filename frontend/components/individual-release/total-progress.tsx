import { CircularProgress } from "@nextui-org/progress";
import { release } from "@/app/release/release.types";

export default function TotalProgress(prop: { releaseInfo: release }) {
    return (
        <div>
            <div>
                <CircularProgress
                    classNames={{
                        svg: "w-96 h-96",
                        value: "text-3xl font-semibold text-white",
                    }}
                    value={64}
                    color="success"
                    label="Total progress"
                    showValueLabel={true}
                ></CircularProgress>
            </div>
            <p>Release name: {prop.releaseInfo.name}</p>
            <p>Release id: {prop.releaseInfo.id_release}</p>
            <p>Release status: {prop.releaseInfo.status}</p>
            <p>Code-cutoff date: {prop.releaseInfo.code_cutoff}</p>
            <p>Release date: {prop.releaseInfo.curr_release_date}</p>
            <p>Release engineer: {prop.releaseInfo.engineer.name}</p>
            {prop.releaseInfo.is_hotfix ? <p>Hotfix</p> : <></>}
            {prop.releaseInfo.is_rollback ? <p>Rolled back</p> : <></>}
            <p>Notes: {prop.releaseInfo.release_note}</p>
        </div>
    );
}
