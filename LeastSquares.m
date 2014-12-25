% Fit the data in a least squares sense with polynomials of degrees 1 through
% 10. Compare the fitted polynomial with erf(t) for values of t between the data
% points. How does the maximum error depend on the polynomial degree?

clear all;
clc;
format long;

for k = 1:11
    t(k) = (k - 1)/10;
    y(k) = erf(t(k));
end
plot(t,y,'ro');
y = y';
hold on;

for degree = 1:10
    
    for i = 0:degree
        X(:,i+1) = t.^(degree-i);
    end
    X(:,degree+1) = ones(11,1);

    beta = X\y;

    x = 0:.001:1;
    
    for j = 0:degree
        B(:,j+1) = x.^(degree-j);
    end
    B(:,degree+1) = ones(1001,1);

    L = B*beta;

    plot(x, L, '-');

    for l = 1:10;
        approx = L(((l-1)*.1 < x) & (x < l*.1))';
        true = erf(x(((l-1)*.1 < x) & (x < l*.1)));
        Errors(:,l) = max(abs(true - approx));
    end
    Errors'
end

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Because erf(t) is an odd function of t, that is, erf(x) = −erf(−x), it is
% reasonable to fit the data by a linear combination of odd powers of t:
% erf(t) ≈ c 1 t + c 2 t 3 + · · · + c n t 2n−1 .
% Again, see how the error between data points depends on n.

clear all;
clc;
format long;

for k = 1:11
    t(k) = (k - 1)/10;
    y(k) = erf(t(k));
end
plot(t,y,'ro');
y = y';
hold on;

for degree = 1:2:19
    
    for i = 0:2:degree
        X(:,i+1) = t.^(degree-i);
    end
    X(:,degree+1) = ones(11,1);

    beta = X\y;
    
    x = 0:.001:1;
    
    for j = 0:2:degree
        B(:,j+1) = x.^(degree-j);
    end
    B(:,degree+1) = ones(1001,1);

    L = B*beta;

    plot(x, L, '-');

    for l = 1:10;
        approx = L(((l-1)*.1 < x) & (x < l*.1))';
        true = erf(x(((l-1)*.1 < x) & (x < l*.1)));
        Errors(:,l) = max(abs(true - approx));
    end
    Errors'
end

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Fit the data with a straight line, y(t) = β 1 + β 2 t, and plot the residuals,
% y(t k ) − y k . You should observe that one of the data points has a much larger
% residual than the others. This is probably an outlier.

clear all; clc;
format long;

t = 1:25;
y = [  5.0291  6.5099  5.3666  4.1272  4.2948
       6.1261 12.5140 10.0502  9.1614  7.5677
       7.2920 10.0357 11.0708 13.4045 12.8415
      11.9666 11.0765 11.7774 14.5701 17.0440
      17.0398 15.9069 15.4850 15.5112 17.6572];
  y = y';
  y = y(:);
  
  plot(t, y, 'co');
  hold on;
  
  X(:,1) = t;
  X(:,2) = ones(25,1);
  
  beta = X\y;
  
  x = 1:25;
  
  B(:,1) = x;
  B(:,2) = ones(length(x), 1);
  
  f = B*beta;
  
  plot(x, f, '-');
  
  r = f - y
 
  plot(t,r,'go', t, zeros(length(t),1), 'k-')

  plot(7, y(7), 'rx', 7, r(7), 'rx');
 
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5
% Discard the outlier, and fit the data again by a straight line. Plot the
% residuals again. Do you see any pattern in the residuals?

clear all; clc;
format long;

t = 1:25;
y = [  5.0291  6.5099  5.3666  4.1272  4.2948
       6.1261 12.5140 10.0502  9.1614  7.5677
       7.2920 10.0357 11.0708 13.4045 12.8415
      11.9666 11.0765 11.7774 14.5701 17.0440
      17.0398 15.9069 15.4850 15.5112 17.6572];
y = y';
y = y(:);

y(7) = [];
t(7) = [];
  
plot(t, y, 'co');
hold on;

X(:,1) = t;
X(:,2) = ones(24,1);
  
beta = X\y;
  
x = 1:25;
x(7) = [];

B(:,1) = x;
B(:,2) = ones(length(x), 1);
  
f = B*beta;
  
plot(x, f, '-');
  
r = f - y;
  
plot(t, r, 'go', t, zeros(length(t),1), 'k-');

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Fit the data, with the outlier excluded, by a model of the form
% y(t) = β 1 + β 2 t + β 3 sin t.

clear all; clc;
format long;

t = 1:25;
y = [  5.0291  6.5099  5.3666  4.1272  4.2948
       6.1261 12.5140 10.0502  9.1614  7.5677
       7.2920 10.0357 11.0708 13.4045 12.8415
      11.9666 11.0765 11.7774 14.5701 17.0440
      17.0398 15.9069 15.4850 15.5112 17.6572];
y = y';
y = y(:);

y(7) = [];
t(7) = [];
  
plot(t, y, 'co');
hold on;

X(:,1) = t;
X(:,2) = sin(t);
X(:,3) = ones(24,1);
  
beta = X\y;
  
x = 1:0.01:26;

B(:,1) = x;
B(:,2) = sin(x);
B(:,3) = ones(length(x), 1);
  
f = B*beta;
  
plot(x, f, '-');
plot(7, 12.5140, 'r*');

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Norris: linear polynomial for calibration of ozone monitors;

clear all; clc;
format long;

y = [ 0.1 338.8 118.1 888.0 9.2 228.1 668.5 998.5 449.1 778.9 559.2 0.3 0.1 778.1 668.8 339.3 448.9 10.8 557.7 228.3 998.0 888.8 119.6 0.3 0.6 557.6 339.3 888.0 998.5 778.9 10.2 117.6 228.9 668.4 449.2 0.2];
  
x = [ 0.2 337.4 118.2 884.6 10.1 226.5 666.3 996.3 448.6 777.0 558.2 0.4 0.6 775.5 666.9 338.0 447.5 11.6 556.0 228.1 995.8 887.6 120.2 0.3 0.3 556.8 339.1 887.2 999.0 779.0 11.1 118.3 229.2 669.1 448.9 0.5];

plot(x,y,'co');
hold on;

X(:,1) = x;
X(:,2) = ones(length(x), 1);

beta = X\y'

f = X*beta;

plot(x, f, '-');

r = y' - f;

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Pontius: quadratic polynomial for calibration of load cells.

clear all; clc;
format long;

y = [.11019 .21956 .32949 .43899 .54803 .65694 .76562 .87487 .98292 1.09146 1.20001 1.30822 1.41599 1.52399 1.63194 1.73947 1.84646 1.95392 2.06128 2.16844 .11052 .22018 .32939 .43886 .54798 .65739 .76596 .87474 .98300 1.09150 1.20004 1.30818 1.41613 1.52408 1.63159 1.73965 1.84696 1.95445 2.06177 2.16829];
x = [150000 300000 450000 600000 750000 900000 1050000 1200000 1350000 1500000 1650000 1800000 1950000 2100000 2250000 2400000 2550000 2700000 2850000 3000000 150000 300000 450000 600000 750000 900000 1050000 1200000 1350000 1500000 1650000 1800000 1950000 2100000 2250000 2400000 2550000 2700000 2850000 3000000];

%plot(x,y,'co');
hold on;

X(:,1) = x;
X(:,2) = x.^2;
X(:,3) = ones(length(x), 1);

beta = X\y';

B(:,1) = x;
B(:,2) = x.^2;
B(:,3) = ones(length(x), 1);

f = B*beta;

%plot(x, f, '-');

r = y' - f;

plot(x, r, 'go', x, zeros(length(x),1), 'k-');

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% The data set is available from the NIST Web site. There is one line for each
% data point. The data are given with the first number on the line a value
% of y, and the second number the corresponding x. The x-values are not
% monotonically ordered, but it is not necessary to sort them. Let n be the
% number of data points and p = 11 the number of polynomial coefficients

clear all; clc; close all;
format long;

y = [0.8116 0.9072 0.9052 0.9039 0.8053 0.8377 0.8667 0.8809 0.7975 0.8162 0.8515 0.8766 0.8885 0.8859 0.8959 0.8913 0.8959 0.8971 0.9021 0.909 0.9139 0.9199 0.8692 0.8872 0.89 0.891 0.8977 0.9035 0.9078 0.7675 0.7705 0.7713 0.7736 0.7775 0.7841 0.7971 0.8329 0.8641 0.8804 0.7668 0.7633 0.7678 0.7697 0.77 0.7749 0.7796 0.7897 0.8131 0.8498 0.8741 0.8061 0.846 0.8751 0.8856 0.8919 0.8934 0.894 0.8957 0.9047 0.9129 0.9209 0.9219 0.7739 0.7681 0.7665 0.7703 0.7702 0.7761 0.7809 0.7961 0.8253 0.8602 0.8809 0.8301 0.8664 0.8834 0.8898 0.8964 0.8963 0.9074 0.9119 0.9228];
x = [-6.860120914 -4.324130045 -4.358625055 -4.358426747 -6.955852379 -6.661145254 -6.355462942 -6.118102026 -7.115148017 -6.815308569 -6.519993057 -6.204119983 -5.853871964 -6.109523091 -5.79832982 -5.482672118 -5.171791386 -4.851705903 -4.517126416 -4.143573228 -3.709075441 -3.499489089 -6.300769497 -5.953504836 -5.642065153 -5.031376979 -4.680685696 -4.329846955 -3.928486195 -8.56735134 -8.363211311 -8.107682739 -7.823908741 -7.522878745 -7.218819279 -6.920818754 -6.628932138 -6.323946875 -5.991399828 -8.781464495 -8.663140179 -8.473531488 -8.247337057 -7.971428747 -7.676129393 -7.352812702 -7.072065318 -6.774174009 -6.478861916 -6.159517513 -6.835647144 -6.53165267 -6.224098421 -5.910094889 -5.598599459 -5.290645224 -4.974284616 -4.64454848 -4.290560426 -3.885055584 -3.408378962 -3.13200249 -8.726767166 -8.66695597 -8.511026475 -8.165388579 -7.886056648 -7.588043762 -7.283412422 -6.995678626 -6.691862621 -6.392544977 -6.067374056 -6.684029655 -6.378719832 -6.065855188 -5.752272167 -5.132414673 -4.811352704 -4.098269308 -3.66174277 -3.2644011];

plot(x,y,'c.');
hold on;

xx = -9:.001:-3;

X(:,1) = x.^10;
X(:,2) = x.^9;
X(:,3) = x.^8;
X(:,4) = x.^7;
X(:,5) = x.^6;
X(:,6) = x.^5;
X(:,7) = x.^4;
X(:,8) = x.^3;
X(:,9) = x.^2;
X(:,10) = x.^1;
X(:,11) = ones(length(x), 1);

beta = X\y';

B1 = polyfit(x,y,10)';        % polyfit
B2 = beta;                    % backslash
B3 = pinv(X)*y';              % Pseudoinverse
B4 = inv(X'*X)*X'*y';         % Normal Equation

u = mean(x);                  % Centering
o = std(x);
t = (x - u)./o;
C(:,1) = t.^10;
C(:,2) = t.^9;
C(:,3) = t.^8;
C(:,4) = t.^7;
C(:,5) = t.^6;
C(:,6) = t.^5;
C(:,7) = t.^4;
C(:,8) = t.^3;
C(:,9) = t.^2;
C(:,10) = t.^1;
C(:,11) = ones(length(t), 1);
B5 = polyfit(t,y,10)';

betaTrue = [-0.0000402962525080404 -0.00246781078275479 -0.0670191154593408 -1.06221498588947 -10.8753180355343 -75.1242017393757 -354.478233703349 -1127.97394098372 -2316.37108160893 -2772.17959193342 -1467.48961422980];  
B6 = betaTrue';

XX(:,1) = xx.^10;
XX(:,2) = xx.^9;
XX(:,3) = xx.^8;
XX(:,4) = xx.^7;
XX(:,5) = xx.^6;
XX(:,6) = xx.^5;
XX(:,7) = xx.^4;
XX(:,8) = xx.^3;
XX(:,9) = xx.^2;
XX(:,10) = xx.^1;
XX(:,11) = ones(length(xx), 1);

P1 = XX*B1;             % polyfit
P2 = XX*B2;             % backslash
P3 = XX*B3;             % Pseudoinverse
P5 = C*B5;              % Centering
P6 = XX*B6;             % Given Beta's

plot(xx,P1,'r-');
plot(xx,P2,'y-');       %P2 = P3 = P5
plot(xx,P3,'g-');
plot(xx,P6,'b-');       %P1 = P6

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% As your first experiment, load the data into Matlab, plot it with ‘.’
% as the line type, and then invoke the Basic Fitting tool available under the
% Tools menu on the figure window. Select the 10th-degree polynomial fit.
% You will be warned that the polynomial is badly conditioned, but ignore that
% for now. How do the computed coefficients compare with the certified values
% on the NIST Web page? How does the plotted fit compare with the graphic
% on the NIST Web page? The basic fitting tool also displays the norm of the
% residuals, ∥r∥. Compare this with the NIST quantity “Residual Standard
% Deviation,” which is
%      ∥r∥ / sqrt(n - p)

clear all; clc; close all;
format long;

y = [0.8116 0.9072 0.9052 0.9039 0.8053 0.8377 0.8667 0.8809 0.7975 0.8162 0.8515 0.8766 0.8885 0.8859 0.8959 0.8913 0.8959 0.8971 0.9021 0.909 0.9139 0.9199 0.8692 0.8872 0.89 0.891 0.8977 0.9035 0.9078 0.7675 0.7705 0.7713 0.7736 0.7775 0.7841 0.7971 0.8329 0.8641 0.8804 0.7668 0.7633 0.7678 0.7697 0.77 0.7749 0.7796 0.7897 0.8131 0.8498 0.8741 0.8061 0.846 0.8751 0.8856 0.8919 0.8934 0.894 0.8957 0.9047 0.9129 0.9209 0.9219 0.7739 0.7681 0.7665 0.7703 0.7702 0.7761 0.7809 0.7961 0.8253 0.8602 0.8809 0.8301 0.8664 0.8834 0.8898 0.8964 0.8963 0.9074 0.9119 0.9228];
x = [-6.860120914 -4.324130045 -4.358625055 -4.358426747 -6.955852379 -6.661145254 -6.355462942 -6.118102026 -7.115148017 -6.815308569 -6.519993057 -6.204119983 -5.853871964 -6.109523091 -5.79832982 -5.482672118 -5.171791386 -4.851705903 -4.517126416 -4.143573228 -3.709075441 -3.499489089 -6.300769497 -5.953504836 -5.642065153 -5.031376979 -4.680685696 -4.329846955 -3.928486195 -8.56735134 -8.363211311 -8.107682739 -7.823908741 -7.522878745 -7.218819279 -6.920818754 -6.628932138 -6.323946875 -5.991399828 -8.781464495 -8.663140179 -8.473531488 -8.247337057 -7.971428747 -7.676129393 -7.352812702 -7.072065318 -6.774174009 -6.478861916 -6.159517513 -6.835647144 -6.53165267 -6.224098421 -5.910094889 -5.598599459 -5.290645224 -4.974284616 -4.64454848 -4.290560426 -3.885055584 -3.408378962 -3.13200249 -8.726767166 -8.66695597 -8.511026475 -8.165388579 -7.886056648 -7.588043762 -7.283412422 -6.995678626 -6.691862621 -6.392544977 -6.067374056 -6.684029655 -6.378719832 -6.065855188 -5.752272167 -5.132414673 -4.811352704 -4.098269308 -3.66174277 -3.2644011];

plot(x,y,'c.');
hold on;

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Examine this data set more carefully by using six different methods to
% compute the polynomial fit. Explain all the warning messages you receive
% during these computations.
% • Polyfit: Use polyfit(x,y,10).
% • Backslash: Use X\y, where X is the n-by-p truncated Vandermonde
% matrix with elements
% X i,j = x p−j
% , i = 1, . . . , n, j = 1, . . . , p.
% i
% • Pseudoinverse: Use pinv(X)*y.
% • Normal equations: Use inv(X’*X)*X’*y.
% • Centering: Let μ = mean(x), σ = std(x), t = (x − μ)/σ.
% Use polyfit(t,y,10).
% • Certified coefficients: Obtain the coefficients from the NIST Web page.

clear all; clc; close all;
format long;

y = [0.8116 0.9072 0.9052 0.9039 0.8053 0.8377 0.8667 0.8809 0.7975 0.8162 0.8515 0.8766 0.8885 0.8859 0.8959 0.8913 0.8959 0.8971 0.9021 0.909 0.9139 0.9199 0.8692 0.8872 0.89 0.891 0.8977 0.9035 0.9078 0.7675 0.7705 0.7713 0.7736 0.7775 0.7841 0.7971 0.8329 0.8641 0.8804 0.7668 0.7633 0.7678 0.7697 0.77 0.7749 0.7796 0.7897 0.8131 0.8498 0.8741 0.8061 0.846 0.8751 0.8856 0.8919 0.8934 0.894 0.8957 0.9047 0.9129 0.9209 0.9219 0.7739 0.7681 0.7665 0.7703 0.7702 0.7761 0.7809 0.7961 0.8253 0.8602 0.8809 0.8301 0.8664 0.8834 0.8898 0.8964 0.8963 0.9074 0.9119 0.9228];
x = [-6.860120914 -4.324130045 -4.358625055 -4.358426747 -6.955852379 -6.661145254 -6.355462942 -6.118102026 -7.115148017 -6.815308569 -6.519993057 -6.204119983 -5.853871964 -6.109523091 -5.79832982 -5.482672118 -5.171791386 -4.851705903 -4.517126416 -4.143573228 -3.709075441 -3.499489089 -6.300769497 -5.953504836 -5.642065153 -5.031376979 -4.680685696 -4.329846955 -3.928486195 -8.56735134 -8.363211311 -8.107682739 -7.823908741 -7.522878745 -7.218819279 -6.920818754 -6.628932138 -6.323946875 -5.991399828 -8.781464495 -8.663140179 -8.473531488 -8.247337057 -7.971428747 -7.676129393 -7.352812702 -7.072065318 -6.774174009 -6.478861916 -6.159517513 -6.835647144 -6.53165267 -6.224098421 -5.910094889 -5.598599459 -5.290645224 -4.974284616 -4.64454848 -4.290560426 -3.885055584 -3.408378962 -3.13200249 -8.726767166 -8.66695597 -8.511026475 -8.165388579 -7.886056648 -7.588043762 -7.283412422 -6.995678626 -6.691862621 -6.392544977 -6.067374056 -6.684029655 -6.378719832 -6.065855188 -5.752272167 -5.132414673 -4.811352704 -4.098269308 -3.66174277 -3.2644011];

plot(x,y,'c.');
hold on;

xx = -9:.001:-3;

X(:,1) = x.^10;
X(:,2) = x.^9;
X(:,3) = x.^8;
X(:,4) = x.^7;
X(:,5) = x.^6;
X(:,6) = x.^5;
X(:,7) = x.^4;
X(:,8) = x.^3;
X(:,9) = x.^2;
X(:,10) = x.^1;
X(:,11) = ones(length(x), 1);

beta = X\y';

B1 = polyfit(x,y,10)';        % polyfit
B2 = beta;                    % backslash
B3 = pinv(X)*y';              % Pseudoinverse
B4 = inv(X'*X)*X'*y';         % Normal Equation

u = mean(x);                  % Centering
o = std(x);
t = (x - u)./o;
C(:,1) = t.^10;
C(:,2) = t.^9;
C(:,3) = t.^8;
C(:,4) = t.^7;
C(:,5) = t.^6;
C(:,6) = t.^5;
C(:,7) = t.^4;
C(:,8) = t.^3;
C(:,9) = t.^2;
C(:,10) = t.^1;
C(:,11) = ones(length(t), 1);
B5 = polyfit(t,y,10)';

betaTrue = [-0.0000402962525080404 -0.00246781078275479 -0.0670191154593408 -1.06221498588947 -10.8753180355343 -75.1242017393757 -354.478233703349 -1127.97394098372 -2316.37108160893 -2772.17959193342 -1467.48961422980];  
B6 = betaTrue';

XX(:,1) = xx.^10;
XX(:,2) = xx.^9;
XX(:,3) = xx.^8;
XX(:,4) = xx.^7;
XX(:,5) = xx.^6;
XX(:,6) = xx.^5;
XX(:,7) = xx.^4;
XX(:,8) = xx.^3;
XX(:,9) = xx.^2;
XX(:,10) = xx.^1;
XX(:,11) = ones(length(xx), 1);

P1 = XX*B1;             % polyfit
P2 = XX*B2;             % backslash
P3 = XX*B3;             % Pseudoinverse
P5 = C*B5;              % Centering
P6 = XX*B6;             % Given Beta's

%polyNorm = norm(y-P1)
%backslashNorm = norm(y'-P2)
%PseudoinverseNorm = norm(y'-P3)
%CenteringNorm = norm(y'-P5)
%GivenBetaNorm = norm(y'-P6)

plot(xx,P1,'r-');
plot(xx,P2,'y-');       %P2 = P3 = P5
plot(xx,P3,'g-');
plot(xx,P6,'b-');       %P1 = P6

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5
% What are the norms of the residuals for the fits computed by the six
% different methods?

clear all; clc; close all;
format long;

y = [0.8116 0.9072 0.9052 0.9039 0.8053 0.8377 0.8667 0.8809 0.7975 0.8162 0.8515 0.8766 0.8885 0.8859 0.8959 0.8913 0.8959 0.8971 0.9021 0.909 0.9139 0.9199 0.8692 0.8872 0.89 0.891 0.8977 0.9035 0.9078 0.7675 0.7705 0.7713 0.7736 0.7775 0.7841 0.7971 0.8329 0.8641 0.8804 0.7668 0.7633 0.7678 0.7697 0.77 0.7749 0.7796 0.7897 0.8131 0.8498 0.8741 0.8061 0.846 0.8751 0.8856 0.8919 0.8934 0.894 0.8957 0.9047 0.9129 0.9209 0.9219 0.7739 0.7681 0.7665 0.7703 0.7702 0.7761 0.7809 0.7961 0.8253 0.8602 0.8809 0.8301 0.8664 0.8834 0.8898 0.8964 0.8963 0.9074 0.9119 0.9228];
x = [-6.860120914 -4.324130045 -4.358625055 -4.358426747 -6.955852379 -6.661145254 -6.355462942 -6.118102026 -7.115148017 -6.815308569 -6.519993057 -6.204119983 -5.853871964 -6.109523091 -5.79832982 -5.482672118 -5.171791386 -4.851705903 -4.517126416 -4.143573228 -3.709075441 -3.499489089 -6.300769497 -5.953504836 -5.642065153 -5.031376979 -4.680685696 -4.329846955 -3.928486195 -8.56735134 -8.363211311 -8.107682739 -7.823908741 -7.522878745 -7.218819279 -6.920818754 -6.628932138 -6.323946875 -5.991399828 -8.781464495 -8.663140179 -8.473531488 -8.247337057 -7.971428747 -7.676129393 -7.352812702 -7.072065318 -6.774174009 -6.478861916 -6.159517513 -6.835647144 -6.53165267 -6.224098421 -5.910094889 -5.598599459 -5.290645224 -4.974284616 -4.64454848 -4.290560426 -3.885055584 -3.408378962 -3.13200249 -8.726767166 -8.66695597 -8.511026475 -8.165388579 -7.886056648 -7.588043762 -7.283412422 -6.995678626 -6.691862621 -6.392544977 -6.067374056 -6.684029655 -6.378719832 -6.065855188 -5.752272167 -5.132414673 -4.811352704 -4.098269308 -3.66174277 -3.2644011];

plot(x,y,'c.');
hold on;

xx = -9:.001:-3;

X(:,1) = x.^10;
X(:,2) = x.^9;
X(:,3) = x.^8;
X(:,4) = x.^7;
X(:,5) = x.^6;
X(:,6) = x.^5;
X(:,7) = x.^4;
X(:,8) = x.^3;
X(:,9) = x.^2;
X(:,10) = x.^1;
X(:,11) = ones(length(x), 1);

beta = X\y';

B1 = polyfit(x,y,10)';        % polyfit
B2 = beta;                    % backslash
B3 = pinv(X)*y';              % Pseudoinverse
B4 = inv(X'*X)*X'*y';         % Normal Equation

u = mean(x);                  % Centering
o = std(x);
t = (x - u)./o;
C(:,1) = t.^10;
C(:,2) = t.^9;
C(:,3) = t.^8;
C(:,4) = t.^7;
C(:,5) = t.^6;
C(:,6) = t.^5;
C(:,7) = t.^4;
C(:,8) = t.^3;
C(:,9) = t.^2;
C(:,10) = t.^1;
C(:,11) = ones(length(t), 1);
B5 = polyfit(t,y,10)';

betaTrue = [-0.0000402962525080404 -0.00246781078275479 -0.0670191154593408 -1.06221498588947 -10.8753180355343 -75.1242017393757 -354.478233703349 -1127.97394098372 -2316.37108160893 -2772.17959193342 -1467.48961422980];  
B6 = betaTrue';

P1 = X*B1;
P2 = X*B2;
P3 = X*B3;
P4 = X*B4;
P5 = C*B5;
P6 = X*B6;

polyNorm = norm(y'-P1)
backslashNorm = norm(y'-P2)
PseudoinverseNorm = norm(y'-P3)
NormalEquationNorm = norm(y' - P4)
CenteringNorm = norm(y'-P5)
GivenBetaNorm = norm(y'-P6)

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Longley data set. The Longley data set of labor statistics was one of the first
% used to test the accuracy of least squares computations. You don’t need to
% go to the NIST Web site to do this problem, but if you are interested in the
% background, you should see the Longley page at [3]. The data set is available
% in NCM in the file longley.dat. You can bring the data into Matlab with
% load longley.dat
% y = longley(:,1);
% X = longley(:,2:7);
% There are 16 observations of 7 variables, gathered over the years 1947 to 1962.
% The variable y and the 6 variables making up the columns of the data matrix
% X are
% y = Total Derived Employment,
% x1 = GNP Implicit Price Deflater,
% x2 = Gross National Product,
% x3 = Unemployment,
% x4 = Size of Armed Forces,
% x5 = Noninstitutional Population Age 14 and Over,
% x6 = Year.
%The objective is to predict y by a linear combination of a constant and the
%six x’s:
% y = B0 + sum(from 1 to 6) of Bk*xk

clear all; clc; close all;
format long;

load longley.dat
y = longley(:,1);
X = longley(:,2:7);

one = ones(length(y), 1);
X = [one, X];

beta = X\y;

P = X*beta;

E = (y - P)/2;

R = corrcoef(X(:,2:end));

y = y - mean(y);
y = y/std(y);

for k = 2:7
    X(:,k) = X(:,k) - mean(X(:,k));
    X(:,k) = X(:,k)/std(X(:,k));
end

C = [y, X(:,2:end)];
plot(C);

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Use the Matlab backslash operator to compute β 0 , β 1 , . . . , β 6 . This
% involves augmenting X with a column of all 1’s, corresponding to the constant
% term.

clear all; clc; close all;
format long;

load longley.dat
y = longley(:,1);
X = longley(:,2:7);

one = ones(length(y), 1);
X = [one, X];

beta = X\y

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Use errorbar to plot y with error bars whose magnitude is the difference
% between y and the least squares fit.

clear all; clc; close all;
format long;

load longley.dat
y = longley(:,1);
X = longley(:,2:7);

one = ones(length(y), 1);
X = [one, X];

beta = X\y;

P = X*beta;

plot(X(:,7),y,'ro');
hold on;

E = (y - P)/2;
errorbar(X(:,7), y, E);

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Use corrcoef to compute the correlation coefficients for X without the
% column of 1’s. Which variables are highly correlated?

clear all; clc; close all;
format long;

load longley.dat
y = longley(:,1);
X = longley(:,2:7);

one = ones(length(y), 1);
X = [one, X];

beta = X\y;

P = X*beta;

%plot(X(:,7),y,'ro');
%hold on;

E = (y - P)/2;
%errorbar(X(:,7), y, E);

R = corrcoef(X(:,2:end))

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Normalize the vector y so that its mean is zero and its standard deviation
% is one. You can do this with
% y = y - mean(y);
% y = y/std(y)
% Do the same thing to the columns of X. Now plot all seven normalized vari-
% ables on the same axis. Include a legend.

clear all; clc; close all;
format long;

load longley.dat
y = longley(:,1);
X = longley(:,2:7);

one = ones(length(y), 1);
X = [one, X];

beta = X\y;

P = X*beta;

hold on;

E = (y - P)/2;

R = corrcoef(X(:,2:end));

y = y - mean(y);
y = y/std(y);

for k = 2:7
    X(:,k) = X(:,k) - mean(X(:,k));
    X(:,k) = X(:,k)/std(X(:,k));
end

C = [y, X(:,2:end)];
plot(C);

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Determine the coefficients in the quadratic form that fits these data in
% the least squares sense by setting one of the coefficients equal to one and
% solving a 10-by-5 overdetermined system of linear equations for the other
% five coefficients. Plot the orbit with x on the x-axis and y on the y-axis.
% Superimpose the ten data points on the plot.

clear all; clc; close all;
format long;

[X,Y] = meshgrid(-0.75:0.01:1.5,-0.5:0.01:1.5);

x = [1.02 .95 .87 .77 .67 .56 .44 .30 .16 .01]';
y = [0.39 .32 .27 .22 .18 .15 .13 .12 .13 .15]';

plot(x,y,'co');
hold on;

M(:,1) = x.^2;
M(:,2) = x.*y;
M(:,3) = y.^2;
M(:,4) = x;
M(:,5) = y;

f = -ones(length(x), 1);

beta = M\f

Z = beta(1)*X.^2 + beta(2)*X.*Y + beta(3)*Y.^2 + beta(4)*X + beta(5)*Y + 1;
contour(X,Y,Z,[0 0])

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% This least squares problem is nearly rank deficient. To see what effect this
% has on the solution, perturb the data slightly by adding to each coordinate
% of each data point a random number uniformly distributed in the interval
% [−.0005, .0005]. Compute the new coefficients resulting from the perturbed
% data. Plot the new orbit on the same plot with the old orbit. Comment on
% your comparison of the sets of coefficients and the orbits.

clear all; clc; close all;
format long;

[X,Y] = meshgrid(-0.75:0.01:1.5,-0.5:0.01:1.5);

x = [1.02 .95 .87 .77 .67 .56 .44 .30 .16 .01]';
y = [0.39 .32 .27 .22 .18 .15 .13 .12 .13 .15]';

plot(x,y,'co');
hold on;

M(:,1) = x.^2;
M(:,2) = x.*y;
M(:,3) = y.^2;
M(:,4) = x;
M(:,5) = y;

f = -ones(length(x), 1);

beta = M\f;

Z = beta(1)*X.^2 + beta(2)*X.*Y + beta(3)*Y.^2 + beta(4)*X + beta(5)*Y + 1;
contour(X,Y,Z,[0 0])

a = -.0005;
b = .0005;
for k = 1:10;
    r = (b-a).*rand(1) + a;
    x(k) = x(k) + r;
end
for k = 1:10;
    r = (b-a).*rand(1) + a;
    y(k) = y(k) + r;
end

N(:,1) = x.^2;
N(:,2) = x.*y;
N(:,3) = y.^2;
N(:,4) = x;
N(:,5) = y;

beta = N\f

Z = beta(1)*X.^2 + beta(2)*X.*Y + beta(3)*Y.^2 + beta(4)*X + beta(5)*Y + 1;
contour(X,Y,Z,[0 0], 'r-')