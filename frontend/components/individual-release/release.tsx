import { release, releaseProgress } from "@/app/release/release.types";
import ReleaseInfo from "./release-info";
import ProgressCharts from "./team-progress";
import TicketsIssuesCard from "./tickets-issues";

export default function Tickets(
    prop: Readonly<{
        releaseInfo: release;
        totalProgress: number;
        progress: releaseProgress;
        ticketsCount: number;
        bugsCount: number;
        date: string;
    }>
) {
    return (
        <div>
            <div style={{ display: "flex", alignItems: "center" }}>
                <TicketsIssuesCard
                    ticketsCount={prop.ticketsCount}
                    bugsCount={prop.bugsCount}
                    releaseId={prop.releaseInfo.id_release}
                    date={prop.date}
                />

                <ReleaseInfo
                    releaseInfo={prop.releaseInfo}
                    totalProgress={prop.totalProgress}
                />
            </div>

            <div>
                <ProgressCharts progress={prop.progress}></ProgressCharts>
            </div>
        </div>
    );
}
