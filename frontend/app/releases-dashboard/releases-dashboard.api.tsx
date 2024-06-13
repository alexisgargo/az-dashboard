import { releaseProgress } from "./releases-dashboard.types";

const API_URL = process.env.NEXT_PUBLIC_API_URL;

export async function getActiveReleasesProgress(
  date: string
): Promise<releaseProgress[]> {
  const response = await fetch(`${API_URL}/releases-historicals/${date}`);
  const data = response.json();
  return data;
}