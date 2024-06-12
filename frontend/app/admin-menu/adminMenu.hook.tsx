import { useEffect, useState } from "react";
import { release } from "../release/release.types";
import { getReleases } from "./adminMenu.api";

const useReleaseList = () => {
    const [releaseList, setReleaseList] = useState<release[]>([]);

    useEffect(() => {
        const fetchReleaseList = async () => {
            setReleaseList(await getReleases());
        };
        fetchReleaseList();
    }, []);

    return { releaseList };
};

export default useReleaseList;
