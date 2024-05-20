import { releaseProgress } from "./releases-dashboard.types";

const API_URL = "http://localhost:8080/";

export async function getActiveReleasesProgress(
  date: string
): Promise<releaseProgress[]> {
  const response = await fetch(`${API_URL}az_dashboard/releases/${date}`);
  const data = response.json();
  return data;
}