import { getRelease, getProgress, getIssueCount, getHistoricalProgress, getHistoricalIssueCount } from "./release.api";
import { issueCount, release, releaseProgress } from "./release.types";
import { SetStateAction, useEffect, useState } from "react";

const useRelease = () => {
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
  
    const [progress, setProgress] = useState<releaseProgress>({
      release: release,
      recordDate: "",
      percent_qa: 0,
      percent_uat: 0,
      percent_third_party: 0,
      percent_pt: 0,
    });
  
    const [issueCount, setIssueCount] = useState<issueCount>({
      bugs: 0,
      issues: 0,
    });
  
    const [totalProgress, setTotalProgress] = useState<number>(0);
    const [showCalendar, setShowCalendar] = useState(false);
    const [ chosenDate, setChosenDate ] = useState("");



    function setSelectedDate(date: string) {
      setChosenDate(date);
    }
  
    useEffect(() => {
      const fetchRelease = async () => {
        setRelease(await getRelease(1));
      };
      fetchRelease();
    }, []);
  
    useEffect(() => {
      const fetchProgress = async () => {
        if (chosenDate != "") {
          setProgress(await getHistoricalProgress(chosenDate, release.id_release));
          setIssueCount(await getHistoricalIssueCount(chosenDate, release.id_release));
        } else {
        setProgress(await getProgress(release.name, release.version));
        setIssueCount(await getIssueCount(release.name));
        }
      };
      fetchProgress();
    }, [release, chosenDate]);
  
  
    const onOpen = () => {
      setShowCalendar(!showCalendar);
    };
  
    useEffect(() => {
      setTotalProgress(
        (progress.percent_qa +
          progress.percent_uat +
          progress.percent_third_party +
          progress.percent_pt) /
        4
      );
    }, [progress]);
  
      return { release, progress, issueCount, totalProgress, showCalendar, setRelease, setProgress, setIssueCount, setChosenDate, setSelectedDate }
    }
    export default useRelease;