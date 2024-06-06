export type filter = {
    column: string;
    label: string;
    selected: string;
};

export type dateFilter = {
    column: string;
    label: string;
    initialDate: string;
    finalDate: string;
};

export type column = {
    key: string;
    label: string;
};
