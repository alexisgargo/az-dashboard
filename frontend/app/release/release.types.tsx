export type release = {
    // from release
    id_release: number; // api params
    name: string;
    label: string;
    engineer: string; // from release_engineer
    code_cutoff: string;
    init_release_date: string;
    end_release_date: string;
    is_hotfix: boolean;
    status: string;
    is_rollback: boolean;
    creation_date: string;
    admin: string; // from admin
    last_modification_date: string;
    release_note: string;
};

export type releaseProgress = {
    // from new table (not created yet)
    id_release: number; // api params
    date: string;
    percent_qa: number;
    percent_uat: number;
    percent_third_party: number;
    percent_pt: number;
};
