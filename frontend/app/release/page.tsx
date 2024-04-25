//import getData from "./release.api";
import ProgressCharts from "@/components/individual-release/team-progress";
import TicketsIssuesCard from "@/components/individual-release/tickets-issues";
import TotalProgress from "@/components/individual-release/total-progress";
import { Button } from "@nextui-org/button";

export default async function ReleasePage() {
  //const personajes = await getData();

  const progressMock = {
    id_release: 42,
    date: "2024-04-25",
    percent_qa: 52,
    percent_uat: 73,
    percent_third_party: 29,
    percent_pt: 91,
  }

  const releaseMock = {
    id_release: 42,
    name: "Release name",
    label: "REL",
    engineer: "David García",
    code_cutoff: "2024-01-01",
    init_release_date: "2024-04-30",
    end_release_date: "2024-04-30",
    is_hotfix: true,
    status: "On track",
    is_rollback: true,
    creation_date: "2024-01-02",
    admin: "Kike Sanchez",
    last_modification_date: "2024-01-02",
    release_note: "Pretty cool ngl."
  }

  return (
    <div>
      <div className="pb-5">
        <Button variant="light"> {"<< Return to dashboard"} </Button>
      </div>

      <div className="grid grid-flow-row grid-flow-col gap-5">
        <div className="row-span-2 pb-5">
          <TotalProgress releaseInfo={releaseMock} />
        </div>

        <div className="flex flex-row gap-5">
          <div className="content-center">
            <TicketsIssuesCard ticketsCount={100} bugsCount={51} />
          </div>
        </div>

        <div className="content-center">
          <ProgressCharts progress={progressMock} />
        </div>
      </div>
    </div>
  );
}