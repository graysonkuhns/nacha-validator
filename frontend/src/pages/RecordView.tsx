import React from 'react';
import { Grid } from '@material-ui/core';

export interface FileHeader {
  kind: "FILE HEADER RECORD";
  count: string;
  destination: string;
  origin: string;
  originName: string;
}

export interface FileControl {
  kind: "FILE CONTROL RECORD";
  count: string;
  entryCount: string;
  entryHash: string;
  debitEntryAmount: string;
  creditEntryAmount: string;
}

export interface BatchHeader {
  kind: "BATCH HEADER RECORD";
  count: string;
  companyName: string;
  companyId: string;
  effectiveDate: string;
}

export interface BatchControl {
  kind: "BATCH CONTROL RECORD";
  count: string;
  entryCount: string;
  entryHash: string;
  debitEntryAmount: string;
  creditEntryAmount: string;
  companyId: string;
}

export type Record = 
  FileHeader |
  FileControl |
  BatchHeader |
  BatchControl;

export interface RecordError {
  field: string;
  reason: string;
}

interface RecordViewProps {
  record: Record,
  errors: RecordError[],
}

interface Item {
  key: string[];
  value: {
    color?: string | undefined;
    text: string;
  };
}

const RecordView: React.FC<RecordViewProps> = ({ record, errors }) => {
  const items: (Item | null)[] = [];

  const getValue = (key: string) => {
    let color = undefined;
    const text = (record as any)[key];
    if (errors.filter(e => e.field === key).length > 0)
      color = 'red';
    return { text, color };
  }

  switch (record.kind) {
    case 'FILE HEADER RECORD':
      items.push({ key: ['Immediate', 'Destitation'], value: getValue('destination') });
      items.push({ key: ['Immediate', 'Origin'], value: getValue('origin') });
      items.push({ key: ['Immediate', 'Origin Name'], value: getValue('originName') });
      break;
    case 'BATCH HEADER RECORD':
      items.push({ key: ['Company', 'Name'], value: getValue('companyName') });
      items.push({ key: ['Company', 'ID'], value: getValue('companyId') });
      items.push({ key: ['Effective', 'Date'], value: getValue('effectiveDate') });
      break;
    case 'BATCH CONTROL RECORD':
      items.push({ key: ['Entry/Addenda', 'Count'], value: getValue('entryCount') });
      items.push({ key: ['Entry Hash', '\u2800'], value: getValue('entryHash') });
      items.push(null);
      items.push({ key: ['TTL Debit Entry', '$-Amount'], value: getValue('debitEntryAmount') });
      items.push({ key: ['TTL Credit Entry', '$-Amount'], value: getValue('creditEntryAmount') });
      items.push({ key: ['Company', 'ID'], value: getValue('companyId') });
      break;
    case 'FILE CONTROL RECORD':
      items.push({ key: ['Entry/Addenda', 'Count'], value: getValue('entryCount') });
      items.push({ key: ['Entry Hash'], value: getValue('entryHash') });
      items.push(null);
      items.push({ key: ['TTL Debit Entry', '$-Amount'], value: getValue('debitEntryAmount') });
      items.push({ key: ['TTL Credit Entry', '$-Amount'], value: getValue('creditEntryAmount') });
      items.push(null);
      break;
  }

  return (
    <Grid container direction="row" spacing={2} style={{
      fontSize: '1.3em',
      padding: '1em',
    }}>
      <Grid item xs={4} style={{
        border: '2px solid black',
        padding: '0.2em',
        textAlign: 'center',
        fontSize: '1.2em',
      }}>
        {`${record.kind} (${record.count})`}
      </Grid>
      <Grid item xs={4} />
      <Grid item xs={4} />
      {items.map((item, index) => (
        <Grid item key={index} xs={4}>
          {item && (
            <Grid container direction="row">
              <Grid item xs={5} style={{
                padding: '0.2em',
                border: '2px solid black',
              }}>
                <Grid container direction="column">
                  {item.key.map((keyPart, index) => (
                    <Grid item key={index} xs={12} style={{
                      textAlign: 'right',
                      wordWrap: 'break-word',
                    }}>
                      {keyPart}
                    </Grid>
                  ))}
                </Grid>
              </Grid>
              <Grid item xs={1} style={{ maxWidth: '3%' }} />
              <Grid item xs={6} style={{
                display: 'flex',
                alignItems: 'center',
                color: item.value.color || 'black',
                border: `1px solid ${item.value.color || 'grey'}`,
              }}>
                <span style={{
                  width: '100%',
                  textAlign: 'right',
                  wordWrap: 'break-word',
                  paddingRight: '0.2em',
                }}>
                  {item.value.text}
                </span>
              </Grid>
            </Grid>
          )}
        </Grid>
      ))}
    </Grid>
  );
};

export default RecordView;