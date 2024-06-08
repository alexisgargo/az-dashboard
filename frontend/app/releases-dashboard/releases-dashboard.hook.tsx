import dayjs from "dayjs";
import { getActiveReleasesProgress } from "./releases-dashboard.api";
import { releaseProgress, release } from "./releases-dashboard.types";
import { useState, useEffect } from "react";

export default function useReleases() {
  const [release, setRelease] = useState<release>({
    id_release: 0,
    name: "",
    version: "",
    engineer: { name: "", id: 0 },
    code_cutoff: "",
    init_release_date: "",
    curr_release_date: "",
    is_hotfix: false,
    status: "",
    is_rollback: false,
    creation_date: "",
    admin: { admin_name: "", admin_password: "", creation_date: "" },
    last_modification_date: "",
    release_note: "",
  });

  const [releases, setReleases] = useState<releaseProgress[]>([{
    release: release,
    recordDate: "",
    percent_qa: 0,
    percent_uat: 0,
    percent_third_party: 0,
    percent_pt: 0,
  }])
  const [totalProgress, setTotalProgress] = useState<number[]>([]);
  const [chosenDate, setChosenDate] = useState(dayjs().toISOString().slice(0, 10));

  function setSelectedDate(date: string) {
    setChosenDate(date);
  }

  useEffect(() => {
    async function fetchReleasesProgress() {
      if (chosenDate) {
        const response = await getActiveReleasesProgress(chosenDate);
        setReleases(response);
      }
    }

    fetchReleasesProgress()

  }, [chosenDate])

  useEffect(() => {
    const totalsList = []
    for (let i = 0; i < releases.length; i++) {
      const releaseTotal = (releases[i].percent_qa +
        releases[i].percent_uat +
        releases[i].percent_third_party +
        releases[i].percent_pt) /
        4
      totalsList.push(releaseTotal)
    }

    setTotalProgress(totalsList)
  }, [releases]);

  return { releases, totalProgress, chosenDate, setSelectedDate }
}