import { FilteredTable } from "@/components/table/filteredTable";
import { columns, filters, dateFilters } from "./issue.types";
import { Divider } from "@nextui-org/divider";
import { getRows } from "./table.api";

export default async function TestTablePage() {
    const rows = await getRows();

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
