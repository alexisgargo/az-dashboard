import { FilteredTable } from "@/components/table/filteredTable";
import { Divider } from "@nextui-org/divider";
import { getIssues } from "../../issue-table.api";
import { columns, filters, dateFilters } from "../../issue-table.types";

export default async function IssueTablePage({params}: any) {
    const issues = await getIssues(params.name, params.version);

    return (
        <div className="w-full">
            <h1 className="text-3xl font-bold text-center">Issues {params.name} {params.version}</h1>
            <Divider className="my-4" />
            <FilteredTable
                columns={columns}
                rows={issues}
                filters={filters}
                dateFilters={dateFilters}
                rowKey="issue_number"
            />
        </div>
    );
}
