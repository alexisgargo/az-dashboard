import { bug } from "./bug-table.types";

const API_URL = process.env.NEXT_PUBLIC_API_URL;

export async function getBugs(id: number, date: string): Promise<bug[]> {
    const res = await fetch(`${API_URL}/issues/${date}/${id}`);
    const data = await res.json();
    return data;
}
