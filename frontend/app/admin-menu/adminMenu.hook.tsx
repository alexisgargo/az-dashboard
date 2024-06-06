import { useEffect, useState } from "react";
import { release } from "../release/release.types";
import { getReleases } from "./adminMenu.api";

const useReleaseList = () => {
    const [releaseList, setReleaseList] = useState<release[]>([{
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
    }]);

    useEffect(() => {
        const fetchReleaseList = async () => {
          setReleaseList(await getReleases());
        };
        fetchReleaseList();
      }, []);

      return { releaseList }
    }

export default useReleaseList;
