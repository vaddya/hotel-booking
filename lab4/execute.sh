#!/bin/bash
sql="$PWD/sql"
data="$PWD/data"
scripts="$(ls sql)"
for script in ${scripts}
do
    if [[ ${script} == "update.sql" || \
          ${script} == "delete-1.sql" || \
          ${script} == "delete-2.sql" ]]; then
        continue
    fi
    sudo -u postgres psql \
         -U postgres \
         -d hotel_booking \
         -f ${sql}/${script} \
            > ${data}/${script%%.*}.txt
done