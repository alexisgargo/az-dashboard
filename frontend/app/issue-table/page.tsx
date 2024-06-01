import { FilteredTable } from "@/components/table/filteredTable";
import { Divider } from "@nextui-org/divider";
// import columns, filters, getrows, datefilters

export default async function IssueTablePage() {
    /* 
    const rows = await getRows();
    */

    return (
        <div className="w-full">
            <h1 className="text-3xl font-bold text-center">Table</h1>
            <Divider className="my-4" />
            <FilteredTable
                columns={columns}
                rows={rows}
                filters={filters}
                dateFilters={dateFilters}
            />
        </div>
    );
}
