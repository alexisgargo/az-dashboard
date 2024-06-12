"use client";
import { FilteredTable } from "@/components/table/filteredTable";
import { Divider } from "@nextui-org/divider";
import { columns, filters, dateFilters } from "../../bug-table.types";
import CalendarRelease from "@/components/individual-release/calendar-release";
import { issue } from "@/app/issue-table/issue-table.types";
import { getBugs } from "../../bug-table.api";
import { useEffect, useState } from "react";
import NavBar from "@/components/navbar/navbar";

export default function BugTablePage({
    params,
}: Readonly<{ params: { id: number; date: string } }>) {
    const [date, setDate] = useState<string>(params.date);
    const [bugs, setBugs] = useState<issue[]>([]);

    useEffect(() => {
        const fetchBugs = async () => {
            setBugs(await getBugs(params.id, date));
        };
        fetchBugs();
    }, [date]);

    return (
        <div className="w-full">
            <NavBar />
            <h1 className="text-3xl font-bold text-center">Bugs</h1>
            <CalendarRelease selectedDate={setDate} date={date} />
            <Divider className="my-4" />
            <FilteredTable
                columns={columns}
                rows={bugs.filter((bug) => bug.is_feature === false)}
                filters={filters}
                dateFilters={dateFilters}
                rowKey="issue_number"
            />
        </div>
    );
}
