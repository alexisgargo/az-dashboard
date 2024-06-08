import {
    getRelease,
    getHistoricalProgress,
    getHistoricalIssueCount,
} from "./release.api";
import { issueCount, release, releaseProgress } from "./release.types";
import { useEffect, useState } from "react";

const useRelease = () => {
    const [release, setRelease] = useState<release>();

    const [progress, setProgress] = useState<releaseProgress>();

    const [issueCount, setIssueCount] = useState<issueCount>({
        bugs: 0,
        issues: 0,
    });

    const [totalProgress, setTotalProgress] = useState<number>(0);
    const [chosenDate, setChosenDate] = useState("");

    function setSelectedDate(date: string) {
        setChosenDate(date);
    }

    useEffect(() => {
        if (release === undefined) return;
        const fetchRelease = async () => {
            setRelease(await getRelease(release.id_release));
        };
        fetchRelease();
    }, []);

    useEffect(() => {
        const fetchProgress = async () => {
            if (release === undefined) return;
            setProgress(
                await getHistoricalProgress(chosenDate, release.id_release)
            );
            setIssueCount(
                await getHistoricalIssueCount(chosenDate, release.id_release)
            );
        };
        fetchProgress();
    }, [release, chosenDate]);

    useEffect(() => {
        if (progress === undefined) return;
        setTotalProgress(
            (progress.percent_qa +
                progress.percent_uat +
                progress.percent_third_party +
                progress.percent_pt) /
                4
        );
    }, [progress]);

    return {
        release,
        progress,
        issueCount,
        totalProgress,
        chosenDate,
        setRelease,
        setProgress,
        setIssueCount,
        setChosenDate,
        setSelectedDate,
    };
};
export default useRelease;
