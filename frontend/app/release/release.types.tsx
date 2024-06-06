export type release = {
    id_release: number;
    name: string;
    version: string;
    engineer: { name: string; id: number };
    code_cutoff: string;
    init_release_date: string;
    curr_release_date: string;
    is_hotfix: boolean;
    status: string;
    is_rollback: boolean;
    creation_date: string;
    admin: {
        admin_name: string;
        admin_password: string;
        creation_date: string;
    };
    last_modification_date: string;
    release_note: string;
};

export type releaseProgress = {
    release: release;
    recordDate: string;
    percent_qa: number;
    percent_uat: number;
    percent_third_party: number;
    percent_pt: number;
};

export type issueCount = {
    bugs: number;
    issues: number;
};
