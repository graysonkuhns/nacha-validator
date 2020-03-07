import React from 'react';
import { Grid } from '@material-ui/core';

interface GrayButtonProps {
  onClick: () => void;
  text: string;
  disabled?: boolean;
}

const GrayButton: React.FC<GrayButtonProps> = ({ onClick, text, disabled }) => {
  return (
    <button onClick={onClick} style={{
      backgroundColor: '#ababab',
      border: '2px solid #292828',
      boxShadow: '0px 4px 4px 1px #ccc',
      color: disabled ? 'grey' : 'black',
      fontSize: '1.7em',
    }}>
      <div style={{
        paddingTop: '0.1em',
        paddingBottom: '0.1em',
        paddingRight: '2.5em',
        paddingLeft: '2.5em',
      }}>
        {text}
      </div>
    </button>
  )
};

const FilePreview: React.FC = () => {
  return (
    <div style={{
      minWidth: '80%',
      minHeight: '60%',
    }}>
      Some text area
    </div>
  );
};

export default function FileView() {
  return (
    <div style={{ border: '1px solid grey', minWidth: '90%', }}>
      <Grid container direction="column">
        <Grid item xs={12}>
          <FilePreview />
        </Grid>
        <Grid item xs={12}>
          <Grid container direction="row" justify="space-around" style={{ padding: '1em' }}>
            <GrayButton
              text="Browse"
              disabled
              onClick={() => {}}
            />
            <GrayButton
              text="Cancel"
              onClick={() => {}}
            />
            <GrayButton
              text="Validate"
              onClick={() => {}}
            />
          </Grid>
        </Grid>
      </Grid>
    </div>
  );
}