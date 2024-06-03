import { FilteredTable } from "@/components/table/filteredTable";
import { Divider } from "@nextui-org/divider";
import { columns, filters, dateFilters } from "../../bug-table.types";
import { getBugs } from "../../bug-table.api";

export default async function BugTablePage({params}: any) {
    const bugs = await getBugs(params.name, params.version);

    return (
        <div className="w-full">
            <h1 className="text-3xl font-bold text-center">Bugs {params.name} {params.version}</h1>
            <Divider className="my-4" />
            <FilteredTable
                columns={columns}
                rows={bugs}
                filters={filters}
                dateFilters={dateFilters}
                rowKey="issue_number"
            />
        </div>
    );
}
