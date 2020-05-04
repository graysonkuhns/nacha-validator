import React, { useState } from 'react';
import FileView from './pages/FileView';
import DetailView from './pages/DetailView';
import { Record, RecordError } from './pages/RecordView';

const toUSD = (value: number): string => {
  return `$${value.toFixed(2)}`;
};

export default function App() {
  const [records, setRecords] = useState<Record[] | null>(null);
  const [errors, setErrors] = useState<RecordError[] | null>(null);

  const handleOnClear = () => {
    setRecords(null);
    setErrors(null);
  };

  const handleOnLoad = (fileContent: string) => {
  };

  const handleOnValidate = (httpRecords: any, httpErrors: any) => {
    const newRecords: Record[] = [];
    const newErrors: RecordError[] = [];

    const rawRecords = httpRecords.sort((a: any, b: any) => a.index - b.index);
    rawRecords.forEach((record: any, index: number) => {
      if (record.type === 'ENTRY_DETAIL')
        return;
      
      if (record.type === 'FILE_HEADER') {
        record.newIndex = newRecords.length;
        newRecords.push({
          kind: 'FILE HEADER RECORD',
          count: '1',
          destination: record.immediateDestination + '',
          origin: record.immediateOrigin + '',
          originName: record.immediateOriginName,
        });
      }
      else if (record.type === 'FILE_CONTROL') {
        record.newIndex = newRecords.length;
        newRecords.push({
          kind: 'FILE CONTROL RECORD',
          count: '9',
          entryCount: record.entryAndAddendaCount + '',
          entryHash: record.entryHash + '',
          debitEntryAmount: toUSD(record.debitAmount),
          creditEntryAmount: toUSD(record.creditAmount),
        });
      }
      else if (record.type === 'BATCH_HEADER') {
        record.newIndex = newRecords.length;
        newRecords.push({
          kind: 'BATCH HEADER RECORD',
          count: '5',
          companyId: record.companyIdentification,
          companyName: record.companyName,
          effectiveDate: record.effectiveEntryDate,
        });
      }
      else if (record.type === 'BATCH_CONTROL') {
        record.newIndex = newRecords.length;
        newRecords.push({
          kind: 'BATCH CONTROL RECORD',
          count: '8',
          entryCount: record.entryAndAddendaCount + '',
          entryHash: record.entryHash + '',
          debitEntryAmount: toUSD(record.debitAmount),
          creditEntryAmount: toUSD(record.creditAmount),
          companyId: record.companyIdentification,
        });
      }
    });

    const rawErrors = httpErrors.sort((a: any, b: any) => a.recordIndex - b.recordIndex);
    rawErrors.forEach((error: any) => {
      if (!error.field.required)
        return;

      // TODO: Hard coded for now.
      //       Convert error field names via camelCase to fieldName on frontend side.
      let [kind, field] = ['', ''];
      const rawField = error.field.name;
      if (rawField === 'BC_CREDIT_AMOUNT') {
        kind = 'BATCH CONTROL RECORD';
        field = 'creditEntryAmount';
      }

      const newRecord = newRecords[rawRecords[error.recordIndex].newIndex];
      newErrors.push({
        kind,
        field,
        reason: `${newRecord.kind} (${newRecord.count}) ${error.errorMessage}`,
        start: error.field.startPosition,
        length: error.field.endPosition - error.field.startPosition,
      });
    });

    setErrors(newErrors);
    setRecords(newRecords);
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
        onValidate={handleOnValidate}
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
