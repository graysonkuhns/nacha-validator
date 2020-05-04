import React from 'react';
import RecordView, { Record, RecordError } from './RecordView';

const Section: React.FC<{ header: string }> = ({ header, children }) => {
  return (
    <>
      <div style={{
        color: 'white',
        padding: '5px',
        minWidth: '90%',
        textAlign: 'center',
        fontSize: '1.3em',
        backgroundColor: 'black',
      }}>
        {header}
      </div>
      <div style={{ padding: '5px' }}>
        {children}
      </div>
    </>
  )
};

interface DetailViewProps {
  records: Record[] | null,
  errors: RecordError[] | null,
}

export default function DetailView({ records, errors }: DetailViewProps) {
  return (
    <>
      <Section header="Message">
        <div style={{
          color: 'red',
          minHeight: '10vh',
          marginLeft: '5%',
          marginRight: '5%',
          display: 'flex',
          flexDirection: 'row',
        }}>
          <div style={{
            fontSize: '3em',
            padding: '1%',
            paddingLeft: '10%',
            paddingRight: '10%',
            textAlign: 'center',
            fontFamily: 'serif',
          }}>
            {errors && 'ERROR!'}
          </div>
          <div style={{
            display: 'flex',
            fontSize: '1.2em',
            flexDirection: 'column',
          }}>
            {(errors || []).map((error, index) => (
              <span key={index}>
                {error.reason}
              </span>
            ))}
          </div>
        </div>
      </Section>
      <Section header="Company Specification / File Details">
        {(records || []).map((record, index) => (
          <RecordView
            key={index}
            record={record}
            errors={errors || []}
          />
        ))}
      </Section>
    </>
  );
}