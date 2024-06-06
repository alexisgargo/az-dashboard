import { issue } from "./issue.types";

export async function getRows(): Promise<issue[]> {
    return [
        {
            id: 1,
            issue_description: "Issue 1",
            issue_number: 1,
            status: "open",
            date: "2021-10-10",
        },
        {
            id: 2,
            issue_description: "Issue 2",
            issue_number: 2,
            status: "closed",
            date: "2021-10-11",
        },
    ];
}
