import { column, filter, dateFilter } from "@/components/table/table.types";

export type issue = {
    id: number;
    issue_description: string;
    issue_number: number;
    status: string;
    date: string;
};

export const columns: column[] = [
    {
        key: "issue_number",
        label: "Number",
    },
    {
        key: "issue_description",
        label: "Description",
    },
    {
        key: "status",
        label: "Status",
    },
    {
        key: "date",
        label: "Date",
    },
];

export const filters: filter[] = [
    {
        column: "status",
        label: "Status",
        selected: "",
    },
    {
        column: "issue_number",
        label: "Number",
        selected: "",
    },
];

export const dateFilters: dateFilter[] = [
    {
        column: "date",
        label: "Date",
        initialDate: "",
        finalDate: "",
    },
];
