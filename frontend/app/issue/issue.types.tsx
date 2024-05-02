export type issue = {
    // from issue
    issue_number: string;
    id_release: number; // api params
    date_saved: string;
    is_feature: boolean;
    issue_description: string;
    issue_status: string;
    created_by: string;
    creation_date: string;
    assignee: string;
    bug_type: string; // 'null' if not a bug
    environmnet: string;
    closed_date: string; // 'null' if not closed
    updates: string;
    created_by_team: string;
};
