export type release = {
    id_release: number;
    name: string;
    version: string;
    engineer: number ;
    code_cutoff: string;
    init_release_date: string;
    curr_release_date: string;
    is_hotfix: boolean;
    status: string;
    is_rollback: boolean;
    creation_date: string;
    admin: number;
    last_modification_date: string;
    release_note: string;
};

export type engineers = [{
    id_engineer:number
    name:string
}]
