import { release, releaseProgress } from "@/app/release/release.types";
import ReleaseInfo from "./release-info";
import ProgressCharts from "./team-progress";
import TicketsIssuesCard from "./tickets-issues";
import calendarRelease from "./calendar-release";
import CalendarRelease from "./calendar-release";

// Input de Componente Release: 
export default function Tickets(prop: {
	selectedDate: any;
	releaseInfo: release, 
	totalProgress: number,
	progress:releaseProgress,
	ticketsCount: number, 
	bugsCount: number ,})
{
  return (
	<div>
		<div>
			<CalendarRelease selectedDate={prop.selectedDate}></CalendarRelease>
		<div>

		</div>
		<div style={{display:'flex',alignItems:'center'}}>
			
			<TicketsIssuesCard ticketsCount={prop.ticketsCount} bugsCount={prop.bugsCount}></TicketsIssuesCard>

			<ReleaseInfo releaseInfo={prop.releaseInfo} totalProgress={prop.totalProgress}></ReleaseInfo>
		</div>
		
		<div>
			<ProgressCharts progress={prop.progress}></ProgressCharts>
		</div>
		</div>
	</div>
  );
}