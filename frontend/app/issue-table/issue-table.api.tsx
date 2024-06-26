import { issue } from "./issue-table.types";

const API_URL = process.env.NEXT_PUBLIC_API_URL;

export async function getIssues(id: number, date: string): Promise<issue[]> {
    const res = await fetch(`${API_URL}/issues/${date}/${id}`);
    const data = await res.json();
    return data;
}
