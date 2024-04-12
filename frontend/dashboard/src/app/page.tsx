'use client';
import { useState } from 'react';
import {Button, ButtonGroup,Table} from "@nextui-org/react";

import {   TableHeader,  TableBody,  TableColumn,  TableRow,  TableCell} from "@nextui-org/react";

interface IssueType {
  key: string;
  status: string;
  description: string;
  author: string;
  summary: string;
  assignee: string;
}
const baseUrl = 'http://localhost:8080';
const url = new URL('project/DAS', baseUrl);
export default function Page() {
  const [issues, setIssues] = useState<IssueType[]>([]);

  const fetchIssues = async () => {
    console.log("Button Issues pressed!")
    try {
      const response = await fetch(url); 
      const data = await response.json();
      setIssues(data);
    } catch (error) {
      console.error('Error fetching Jira issues:', error);
    }
  };

  return (
    <><div>
          <Button onPress={() => console.log("Button pressed!")}>
              Click Me
          </Button>

          <Button onPress={fetchIssues}>Load Jira Issues</Button>
          <Table
              aria-label="Jira Issues"

          >
              <TableHeader>
                  <TableColumn>Key</TableColumn>
                  <TableColumn>Status</TableColumn>
                  <TableColumn>Description</TableColumn>
                  <TableColumn>Author</TableColumn>
                  <TableColumn>Summary</TableColumn>
                  <TableColumn>Assignee</TableColumn>
              </TableHeader>
              <TableBody>
                  {issues.map((issue) => (
                      <TableRow key={issue.key}>
                          <TableCell>{issue.key}</TableCell>
                          <TableCell>{issue.status}</TableCell>
                          <TableCell>{issue.description}</TableCell>
                          <TableCell>{issue.author}</TableCell>
                          <TableCell>{issue.summary}</TableCell>
                          <TableCell>{issue.assignee}</TableCell>
                      </TableRow>
                  ))}
              </TableBody>
          </Table>
      </div><a
          href="https://nextjs.org/docs?utm_source=create-next-app&utm_medium=appdir-template&utm_campaign=create-next-app"
          className="group rounded-lg border border-transparent px-5 py-4 transition-colors hover:border-gray-300 hover:bg-gray-100 hover:dark:border-neutral-700 hover:dark:bg-neutral-800/30"
          target="_blank"
          rel="noopener noreferrer"
      >
              <h2 className="mb-3 text-2xl font-semibold">
                  Docs{" "}
                  <span className="inline-block transition-transform group-hover:translate-x-1 motion-reduce:transform-none">
                      -&gt;
                  </span>
              </h2>
              <p className="m-0 max-w-[30ch] text-sm opacity-50">
                  Find in-depth information about Next.js features and API.
              </p>
          </a><a
              href="https://nextjs.org/learn?utm_source=create-next-app&utm_medium=appdir-template-tw&utm_campaign=create-next-app"
              className="group rounded-lg border border-transparent px-5 py-4 transition-colors hover:border-gray-300 hover:bg-gray-100 hover:dark:border-neutral-700 hover:dark:bg-neutral-800/30"
              target="_blank"
              rel="noopener noreferrer"
          >
              <h2 className="mb-3 text-2xl font-semibold">
                  Learn{" "}
                  <span className="inline-block transition-transform group-hover:translate-x-1 motion-reduce:transform-none">
                      -&gt;
                  </span>
              </h2>
              <p className="m-0 max-w-[30ch] text-sm opacity-50">
                  Learn about Next.js in an interactive course with&nbsp;quizzes!
              </p>
          </a><a
              href="https://vercel.com/templates?framework=next.js&utm_source=create-next-app&utm_medium=appdir-template&utm_campaign=create-next-app"
              className="group rounded-lg border border-transparent px-5 py-4 transition-colors hover:border-gray-300 hover:bg-gray-100 hover:dark:border-neutral-700 hover:dark:bg-neutral-800/30"
              target="_blank"
              rel="noopener noreferrer"
          >
              <h2 className="mb-3 text-2xl font-semibold">
                  Templates{" "}
                  <span className="inline-block transition-transform group-hover:translate-x-1 motion-reduce:transform-none">
                      -&gt;
                  </span>
              </h2>
              <p className="m-0 max-w-[30ch] text-sm opacity-50">
                  Explore starter templates for Next.js.
              </p>
          </a><a
              href="https://vercel.com/new?utm_source=create-next-app&utm_medium=appdir-template&utm_campaign=create-next-app"
              className="group rounded-lg border border-transparent px-5 py-4 transition-colors hover:border-gray-300 hover:bg-gray-100 hover:dark:border-neutral-700 hover:dark:bg-neutral-800/30"
              target="_blank"
              rel="noopener noreferrer"
          >
              <h2 className="mb-3 text-2xl font-semibold">
                  Deploy{" "}
                  <span className="inline-block transition-transform group-hover:translate-x-1 motion-reduce:transform-none">
                      -&gt;
                  </span>
              </h2>
              <p className="m-0 max-w-[30ch] text-balance text-sm opacity-50">
                  Instantly deploy your Next.js site to a shareable URL with Vercel.
              </p>
          </a></>
  );
}
