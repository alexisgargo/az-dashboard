"use client";
import { useMemo, useState, FC } from "react";
import {
    Table,
    TableHeader,
    TableColumn,
    TableBody,
    TableRow,
    TableCell,
    getKeyValue,
    SortDescriptor,
} from "@nextui-org/table";
import { Input } from "@nextui-org/input";
import { column, filter, dateFilter } from "./table.types";

export interface TableProps {
    columns: column[];
    rows: Record<string, any>[];
    filters: filter[];
    dateFilters: dateFilter[];
}

export const FilteredTable: FC<TableProps> = (props) => {
    const [sortDescriptor, setSortDescriptor] = useState<SortDescriptor>({
        column: props.columns[0].key, // Asegúrate de que esto sea un valor válido
        direction: "ascending",
    });
    const [filters, setFilters] = useState(props.filters);
    const [dateFilters, setDateFilters] = useState(props.dateFilters);
    const [isFilterDate, setIsFilterDate] = useState(false);

    const filteredRows = useMemo(() => {
        let filteredRows = [...props.rows];

        // Aplicación de los filtros de fecha
        dateFilters.forEach((filter) => {
            if (filter.initialDate && filter.finalDate) {
                filteredRows = filteredRows.filter((row) => {
                    const dateValue = new Date(row[filter.column]);
                    const initialDate = new Date(filter.initialDate);
                    const finalDate = new Date(filter.finalDate);
                    return dateValue >= initialDate && dateValue <= finalDate;
                });
            }
        });

        // Aplicación de los filtros de texto
        filters.forEach((filter) => {
            if (filter.selected) {
                filteredRows = filteredRows.filter((row) => {
                    return row[filter.column]
                        .toString()
                        .toLowerCase()
                        .includes(filter.selected.toLowerCase());
                });
            }
        });

        return filteredRows;
    }, [props.rows, filters, dateFilters]);

    const sortedRows = useMemo(() => {
        if (!sortDescriptor.column) {
            return filteredRows;
        }

        return [...filteredRows].sort((a, b) => {
            const first = a[sortDescriptor.column];
            const second = b[sortDescriptor.column];
            const cmp = first < second ? -1 : first > second ? 1 : 0;

            return sortDescriptor.direction === "descending" ? -cmp : cmp;
        });
    }, [sortDescriptor, filteredRows]);

    const searchFilters = useMemo(() => {
        return (
            <div className="flex flex-col gap-4">
                <div className="flex justify-between gap-3 items-end">
                    {filters.map((filter) => (
                        <Input
                            key={filter.column}
                            isClearable
                            label={"Search by " + filter.label}
                            fullWidth
                            value={filter.selected}
                            onClear={() =>
                                setFilters((prev) => {
                                    const newFilters = [...prev];
                                    const index = newFilters.findIndex(
                                        (f) => f.column === filter.column
                                    );
                                    newFilters[index].selected = "";
                                    setIsFilterDate(false);
                                    return newFilters;
                                })
                            }
                            onValueChange={(e) =>
                                setFilters((prev) => {
                                    const newFilters = [...prev];
                                    const index = newFilters.findIndex(
                                        (f) => f.column === filter.column
                                    );
                                    newFilters[index].selected = e;
                                    setIsFilterDate(false);
                                    return newFilters;
                                })
                            }
                        />
                    ))}
                </div>
                <div className="flex flex-row gap-4">
                    {dateFilters.map((filter) => (
                        <div key={filter.column} className="flex flex-col gap-4">
                            <div className="flex justify-between gap-3 items-end">
                                <Input
                                    label={"Start of " + filter.label}
                                    type="date"
                                    fullWidth
                                    className="w-full"
                                    placeholder="YYYY-MM-DD"
                                    onValueChange={(e) => {
                                        setDateFilters((prev) => {
                                            const newFilters = [...prev];
                                            const index = newFilters.findIndex(
                                                (f) =>
                                                    f.column === filter.column
                                            );
                                            newFilters[index].initialDate = e;
                                            setIsFilterDate(true);
                                            return newFilters;
                                        });
                                    }}
                                />
                                <Input
                                    label={"End of " + filter.label}
                                    type="date"
                                    fullWidth
                                    className="w-full"
                                    placeholder="YYYY-MM-DD"
                                    onValueChange={(e) => {
                                        setDateFilters((prev) => {
                                            const newFilters = [...prev];
                                            const index = newFilters.findIndex(
                                                (f) =>
                                                    f.column === filter.column
                                            );
                                            newFilters[index].finalDate = e;
                                            setIsFilterDate(true);
                                            return newFilters;
                                        });
                                    }}
                                />
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        );
    }, [filters, dateFilters]);

    return (
        <div className="overflow-x-auto">
            <Table
                aria-label="Example table with dynamic content"
                sortDescriptor={sortDescriptor}
                onSortChange={setSortDescriptor}
                topContent={searchFilters}
            >
                <TableHeader columns={props.columns}>
                    {(column) => (
                        <TableColumn key={column.key} allowsSorting>
                            {column.label}
                        </TableColumn>
                    )}
                </TableHeader>
                <TableBody emptyContent={"No records found"} items={sortedRows}>
                    {(row) => (
                        <TableRow key={row.id_release}>
                            {(columnKey) => (
                                <TableCell>
                                    {row[columnKey]}
                                </TableCell>
                            )}
                        </TableRow>
                    )}
                </TableBody>
            </Table>
        </div>
    );
};
