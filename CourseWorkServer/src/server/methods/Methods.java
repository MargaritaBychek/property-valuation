package server.methods;

import model.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Math.pow;

public class Methods {
        private static double Ca=16.9;//арендная ставка
        private static double PriceBuilding=260;//стоимость 1 м3  типичного сооружения
        private static double PriceLand=200;//стоимость 1 м2 земли
        private static double R=0.07;//ставка капитализации
        private static double dr=0.175;//ставка дисконтирования
        private static int YEAR_LOST=400;//ежегодные потери
        private static int n=10;
        private static double NDS=0.175;

        //Дисконтирование денежных потоков
        public static double cashFlowDiscounting(Realty realty, Commission commission)
        {
            double NOI=Methods.findNOI(realty,commission);
            double V=0;
            for(int j=1;j<=n;j++)
            {
                V+=(NOI/pow((1+dr),j))+(V/(pow((1+dr),n)));
            }
            return V;
        }
        //Капитализации
        public static double capitalization(Realty realty, Commission commission)
        {
            System.out.println(realty.toString());
            double NOI=Methods.findNOI(realty,commission);
            double V=NOI/R;
            return V;
        }
        //Сравнительной 1
        public static double comparativeUnit(Realty realty, Commission commission)
        {
           double Vr=Ca*realty.getSquareFloor()*
                    realty.getHighCeil()* realty.getMaterialCoefficient()*
                    realty.getStateCoefficient() ;//полная стоимость замещения
            double Vl=realty.getSquareFloor()*
                    realty.getHighCeil()*realty.getThickWall()*
                    realty.getMaterialCoefficient()*PriceBuilding;//стоимость земельного участк
            double D=realty.getRooms()*realty.getThickWall()/NDS*YEAR_LOST;
            double V=Vl+Vr-D;
            return V;
        }

        public static double findNOI(Realty realty, Commission commission)
        {
            double PGI=Ca*realty.getSquareFloor()*realty.getHighCeil()*
                    realty.getMaterialCoefficient()*
                    realty.getStateCoefficient();//потенциальный валовый доход
            Date dateNow=new Date();
            SimpleDateFormat formatNew=new SimpleDateFormat("yyyy-MM-dd");
            String date=formatNew.format(dateNow);
            String[] words=commission.getDate().split("-");
            int year1=Integer.parseInt(words[0]);
            double month1=Double.parseDouble(words[1]);
            double day1=Double.parseDouble(words[2]);
            String[] word=date.split("-");
            int year2=Integer.parseInt(word[0]);
            double month2=Double.parseDouble(word[1]);
            double day2=Double.parseDouble(word[2]);
            double difference=year2-year1+(month2-month1)/12+(day2-day1)/30;
            double Lost=difference*YEAR_LOST;//потери
            double RGI=PGI-Lost;//действительный валовый доход
            double Consumption=realty.getThickWall()/NDS*YEAR_LOST;//Операционные расходы
            double NOI=RGI-Consumption;//Чистый операционный доход
            return NOI;

        }

        public static double assessFlat(Realty realty, Flat flat, Commission commission, int id)
        {
            double V=0;
            switch (id){
                case 1:V=comparativeUnit(realty,commission);break;
                case 2:V=capitalization(realty,commission);break;
                case 3:V=cashFlowDiscounting(realty,commission);break;
            }
            if(flat.isLast_FirstFloor())
                V*=0.95;
            if(flat.isCorner())
                V*=0.96;
            if(flat.isRepair())
                V*=1.24;
            if(flat.isLoggia())
                V*=1.11;
            if(flat.isGreenZone())
                V*=1.08;
            if(flat.isDormitoryArea())
                V*=1.005;
            return V;
        }
        public static double assessHouse(Realty realty, House house, Commission commission, int id)
        {
            double V=0;
            switch (id){
                case 1:V=comparativeUnit(realty,commission);break;
                case 2:V=capitalization(realty,commission);break;
                case 3:V=cashFlowDiscounting(realty,commission);break;
            }
            double v1=house.getSquareLand()*PriceLand*realty.getStateCoefficient();
            if(house.isCHS())
                V*=1.35;
            if(house.isAqueduct())
                V*=1.19;
            return V+v1;
        }
        public static double assessOffice(Realty realty, Office office, Commission commission, int id)
        {
            double V=0;
            switch (id){
                case 1:V=comparativeUnit(realty,commission);break;
                case 2:V=capitalization(realty,commission);break;
                case 3:V=cashFlowDiscounting(realty,commission);break;
            }
            V-=office.getMetresToRoad()*0.004;
            if(office.isLoft())
                V*=1.11;
            if(office.isParking())
                V*=1.08;
            if(office.isCenter())
                V*=1.39;
            return V;
        }

    }

