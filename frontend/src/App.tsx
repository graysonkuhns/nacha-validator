import React, { useState } from 'react';
import FileView from './pages/FileView';
import DetailView from './pages/DetailView';
import { Record, RecordError } from './pages/RecordView';

const mockRecords: Record[] = [
  {
    kind: 'FILE HEADER RECORD',
    count: '1',
    destination: '101000019',
    origin: '741258964',
    originName: 'THE FAB FOUR CORP',
  },
  {
    kind: 'BATCH HEADER RECORD',
    count: '5',
    companyName: 'STRAWBERRYFIELDS',
    companyId: '741258964',
    effectiveDate: '10/31/2019',
  },
  {
    kind: 'BATCH CONTROL RECORD',
    count: '8',
    entryCount: '18',
    entryHash: '0181800018',
    debitEntryAmount: '$0.00',
    creditEntryAmount: '$0.83',
    companyId: '741258964',
  },
  {
    kind: 'FILE CONTROL RECORD',
    count: '9',
    entryCount: '18',
    entryHash: '0181800018',
    debitEntryAmount: '$0.00',
    creditEntryAmount: '$0.83',
  },
];

const mockErrors: RecordError[] = [
  {
    field: 'debitEntryAmount',
    reason: 'BATCH CONTROL RECORD (8) TTL Debit Entry $-Amount Does NOT Match Entry Totals',
    start: 64,
    length: 10,
  },
  {
    field: 'creditEntryAmount',
    reason: 'BATCH CONTROL RECORD (8) TTL Credit Entry $-Amount Does NOT Match Entry Totals',
    start: 74,
    length: 10,
  },
  {
    field: 'debitEntryAmount',
    reason: 'FILE CONTROL RECORD (9) TTL Debit Entry $-Amount Does NOT Match Entry Totals',
    start: 104,
    length: 10,
  },
  {
    field: 'creditEntryAmount',
    reason: 'FILE CONTROL RECORD (9) TTL Credit Entry $-Amount Does NOT Match Entry Totals',
    start: 114,
    length: 10,
  },
];

export default function App() {
  const [records, setRecords] = useState<Record[] | null>(null);
  const [errors, setErrors] = useState<RecordError[] | null>(null);

  const handleOnClear = () => {
    setRecords(null);
    setErrors(null);
  };

  const handleOnLoad = () => {
    setRecords(mockRecords);
    setErrors(mockErrors);
  };

  return (
    <div style={{
      height: '100%',
      width: '70%',
      marginLeft: '15%',
      marginRight: '15%',
      border: '1px solid black',
    }}>
      <FileView
        onClear={handleOnClear}
        onLoad={handleOnLoad}
      />
      <DetailView
        records={records}
        errors={errors}
      />
    </div>
  );
}
