"use client";
import { FilteredTable } from "@/components/table/filteredTable";
import { Divider } from "@nextui-org/divider";
import { getIssues } from "../../issue-table.api";
import { columns, filters, dateFilters } from "../../issue-table.types";
import CalendarRelease from "@/components/individual-release/calendar-release";
import { useEffect, useState } from "react";
import { issue } from "../../issue-table.types";
import NavBar from "@/components/navbar/navbar";

export default function IssueTablePage({
    params,
}: Readonly<{ params: { id: number; date: string } }>) {
    const [date, setDate] = useState<string>(params.date);
    const [issues, setIssues] = useState<issue[]>([]);

    useEffect(() => {
        const fetchIssues = async () => {
            setIssues(await getIssues(params.id, date));
        };
        fetchIssues();
    }, [date]);

    return (
        <div className="w-full">
            <NavBar />
            <h1 className="text-3xl font-bold text-center">Issues</h1>
            <CalendarRelease selectedDate={setDate} date={date} />
            <Divider className="my-4" />
            <FilteredTable
                columns={columns}
                rows={issues.filter((issue) => issue.is_feature)}
                filters={filters}
                dateFilters={dateFilters}
                rowKey="issue_number"
            />
        </div>
    );
}
