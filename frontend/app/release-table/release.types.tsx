import { column, filter, dateFilter } from "@/components/table/table.types";

export type release = {
    id_release: number;
    name: string;
    version: string;
    engineer_name: string;
    admin_name: string;
    code_cutoff: string;
    init_release_date: string;
    curr_release_date: string;
    creation_date: string;
    last_modification_date: string;
    is_hotfix: boolean;
    status: string;
    is_rollback: boolean;
    release_note: string;
};

export const columns: column[] = [
    { key: "id_release", label: "ID" },
    { key: "name", label: "Name" },
    { key: "version", label: "Version" },
    { key: "engineer_name", label: "Engineer" },
    { key: "admin_name", label: "Admin" },
    { key: "code_cutoff", label: "Code Cutoff" },
    { key: "init_release_date", label: "Init Release Date" },
    { key: "curr_release_date", label: "Current Release Date" },
    { key: "creation_date", label: "Creation Date" },
    { key: "last_modification_date", label: "Last Modification Date" },
    { key: "is_hotfix", label: "Hotfix" },
    { key: "status", label: "Status" },
    { key: "is_rollback", label: "Rollback" },
    { key: "release_note", label: "Release Note" },
];

export const filters: filter[] = [
    { column: "status", label: "Status", selected: "" },
    { column: "version", label: "Version", selected: "" },
];

export const dateFilters: dateFilter[] = [
    { column: "code_cutoff", label: "Code Cutoff Date", initialDate: "", finalDate: "" },
    { column: "init_release_date", label: "Initial Release Date", initialDate: "", finalDate: "" },
    { column: "curr_release_date", label: "Current Release Date", initialDate: "", finalDate: "" },
];
