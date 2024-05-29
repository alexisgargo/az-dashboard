import { column, filter, dateFilter } from "@/components/table/table.types";

export type release = {
    id_release: number;
    name: string;
    version: string;
    engineer: { name: string; id: number };
    engineer_name: string;
    code_cutoff: string;
    init_release_date: string;
    curr_release_date: string;
    is_hotfix: boolean;
    hotfix: string;
    status: string;
    is_rollback: boolean;
    rollback: string;
    creation_date: string;
    admin: {
        admin_name: string;
        admin_password: string;
        creation_date: string;
    };
    admin_name: string;
    last_modification_date: string;
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
    { key: "hotfix", label: "Hotfix" },
    { key: "status", label: "Status" },
    { key: "rollback", label: "Rollback" },
    { key: "release_note", label: "Release Note" },
];

export const filters: filter[] = [
    { column: "name", label: "Name", selected: "" },
    { column: "engineer_name", label: "Engineer", selected: "" },
    { column: "admin_name", label: "Admin", selected: "" },
    { column: "status", label: "Status", selected: "" },
    { column: "hotfix", label: "Hotfix", selected: "" },
    { column: "rollback", label: "Rollback", selected: "" },
];

export const dateFilters: dateFilter[] = [
    {
        column: "code_cutoff",
        label: "Code Cutoff Date",
        initialDate: "",
        finalDate: "",
    },
    {
        column: "curr_release_date",
        label: "Current Release Date",
        initialDate: "",
        finalDate: "",
    },
    {
        column: "creation_date",
        label: "Creation Date",
        initialDate: "",
        finalDate: "",
    },
];
