import { FilteredTable } from "@/components/table/filteredTable";
import { columns, filters, dateFilters } from "./release.types";
import { Divider } from "@nextui-org/divider";
import { getRows } from "./release.table.api";

export default async function ReleaseTablePage() {
    const rows = await getRows();

    return (
        <div className="w-full">
            <h1 className="text-3xl font-bold text-center">Releases Table</h1>
            <Divider className="my-4" />
            <FilteredTable
                columns={columns}
                rows={rows}
                filters={filters}
                dateFilters={dateFilters}
                rowKey="id_release"
            />
        </div>
    );
}
