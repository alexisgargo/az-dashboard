import { column, filter, dateFilter } from "@/components/table/table.types";

export type bug = {

    issue_number: string;
    issue_status: string;
    //id_release: number; // api params
    //date_saved: string;
    issue_summary: string;
    created_by: string;
    is_feature: boolean;
    creation_date: string;
    updates: string;
    assignee: string;
    environmnet: string;
    release: string;
    record_date: string;
    record_time: string;
    //closed_date: string; // 'null' if not closed
    //created_by_team: string;
};

export const columns: column[] = [
    { key: "issue_number", label: "Issue Number" },
    { key: "issue_status", label: "Status" },
    { key: "issue_summary", label: "Summary" },
    { key: "created_by", label: "Created by" },
    { key: "creation_date", label: "Creation Date" },
    { key: "updates", label: "Updates" },
    { key: "assignee", label: "Assignee" },
    { key: "environment", label: "Environment" },
    //{ key: "close_date", label: "Close Date" },
];

export const filters: filter[] = [
    { column: "issue_number", label: "Issue Number", selected: "" },
    { column: "assignee", label: "Assignee", selected: "" },
    { column: "environment", label: "Environment", selected: ""  },
    { column: "created_by", label: "Created by", selected: "" },
    { column: "issue_status", label: "Status", selected: "" },
    { column: "issue_summary", label: "Summary", selected: "" },
    { column: "updates", label: "Updates", selected: "" },

];

export const dateFilters: dateFilter[] = [
    { 
        column: "creation_date", 
        label: "Creation Date", 
        initialDate: "",
        finalDate: "",
    },
    // { 
    //     column: "close_date", 
    //     label: "Close Date", 
    //     initialDate: "",
    //     finalDate: "",
    // },
    // { 
    //     column: "record_date", 
    //     label: "Record Date", 
    //     initialDate: "",
    //     finalDate: "",
    // },
];